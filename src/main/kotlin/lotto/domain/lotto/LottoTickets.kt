package lotto.domain.lotto

import lotto.domain.matcher.Count
import lotto.domain.matcher.MatchResult
import lotto.domain.rank.Rank

class LottoTickets(
    val lottoTickets: List<LottoTicket>
) {
    fun match(winningNumbers: WinningLotto, bonusNumber: BonusNumber): MatchResult =
        MatchResult(
            lottoTickets
                .map { it.match(winningNumbers, bonusNumber) }
                .groupingBy { rank: Rank -> rank }
                .eachCount()
                .mapValues { Count(it.value) }
        )

    fun merge(other: LottoTickets): LottoTickets =
        LottoTickets(
            lottoTickets.plus(other.lottoTickets)
        )

    fun toInts(): List<List<Int>> {
        return lottoTickets.map { lottoTicket ->
            lottoTicket.lottoNumbers.toInts()
        }
    }
}