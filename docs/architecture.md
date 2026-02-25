# Gamified Life OS - Android Architecture Blueprint

## 1) Project Structure (Mobile-first, Kotlin + Compose)

```text
app/
  src/main/java/com/aura/gamifiedlifeos/
    MainActivity.kt
    data/
      local/
        AppDatabase.kt
        Converters.kt
        dao/
        entity/
      model/
    domain/
      ProgressionEngine.kt
    repository/
      GameRepository.kt
    ui/
      dashboard/
      theme/
```

### Layer responsibilities
- **UI (Compose):** Dashboard + future Character, Shop, History, Habit Builder screens.
- **Domain:** deterministic game math (AP/MB/CC payouts, momentum, anti-quit decay).
- **Data:** Room entities/DAOs + repository orchestration.

## 2) Room Database Schema

### `user_profile`
Tracks single-player progression and anti-quit state:
- `level`, `auraPoints` (AP; never spent)
- `mewBucks` (MB; spendable dopamine currency)
- `chronoChirals` (CC; insurance currency)
- `momentumScore`, `momentumState`, `momentumFloor`, `streakDays`, `snoozePasses`

### `stat_progress`
Auto-growing RPG stats (INT/STR/AGI/CHA/DEX/WIS) per user:
- unique constraint on `(userId, statType)`
- stores cumulative `points` and derived stat `level`

### `habit_action`
Custom habit/action templates from the Habit Builder:
- difficulty and energy fields (`difficulty`, `energyCost`)
- reward baselines (`baseApReward`, `baseMbReward`, `baseCcChancePercent`)
- `isLowEnergyFriendly` for bad-day accessibility

### `action_log`
Immutable event timeline for completed actions:
- snapshots of title/difficulty and exact AP/MB/CC awarded
- captures momentum transition (`momentumBefore`, `momentumAfter`)
- powers History/Log analytics

### `daily_summary`
Daily rollups for compact history cards and missed-day handling:
- totals (`totalAp`, `totalMb`, `totalCc`)
- forgiveness bookkeeping (`missedPenaltyApplied`, `forgivenessUsed`)

## 3) Progression Math (Core Logic Manager)

`ProgressionEngine.resolveAction()` computes rewards using:
- Difficulty factor: `0.8 + difficulty * 0.15`
- Momentum multiplier: LOW `0.9`, STABLE `1.05`, FLOW `1.2`
- Low-Energy mode modifier: `0.85`
- Streak bonus cap: up to `+0.2`

AP payout:
```text
apAward = baseAp * ((difficultyFactor + streakBonus) * momentumMultiplier * lowEnergyMultiplier)
```

MB payout:
```text
mbAward = baseMb * (0.75 + difficulty * 0.1) * lowEnergyMultiplier
```

CC insurance chance:
- random roll against `baseCcChancePercent`

Momentum evolution:
- score increases with action completion
- state thresholds:
  - LOW `< 30`
  - STABLE `30..71`
  - FLOW `72+`

Forgiving missed-day decay:
- `applyMissedDayDecay()` uses soft decay (`*0.93`) with floor protection.

## 4) Dashboard Foundation

`DashboardScreen` currently ships with:
- AP progress bar + level
- Momentum meter (color-coded LOW/STABLE/FLOW)
- MB + CC balances
- quick-tap action cards
- **Low-Energy Mode** switch for bad days

This is production-ready as a baseline scaffold and can be connected directly to Room-backed state via repository + ViewModel flows.
