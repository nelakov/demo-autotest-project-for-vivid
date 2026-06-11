# Modularity Review

**Scope**: Entire codebase — all 11 source classes across `config/`, `helpers/`, `tests/`, and `tests/pages/` of the Vivid Money UI test-automation suite.
**Date**: 2026-06-12

## Executive Summary

This project is a Selenide + JUnit5 UI test-automation suite that drives the live Vivid Money web app and reports results through Allure. Its internal [modularity](https://coupling.dev/posts/core-concepts/modularity/) is **healthy**: the configuration, driver, and reporting helpers are cohesive, and the test classes orchestrate page objects through clean fluent chains. Every in-repo integration is high-strength but low-[distance](https://coupling.dev/posts/dimensions-of-coupling/distance/) — which is [balanced](https://coupling.dev/posts/core-concepts/balance/), so no internal decomposition is warranted. The one **critical** imbalance is external: the page objects couple [intrusively](https://coupling.dev/posts/dimensions-of-coupling/integration-strength/) to the *system under test* — depending on CSS-module hash selectors (private implementation details) and exact marketing copy of a separately-owned, independently-deployed, highly [volatile](https://coupling.dev/posts/dimensions-of-coupling/volatility/) frontend. Two minor cohesion notes remain (`DriverUtils` grab-bag, a three-hop video-URL chain); both are tolerable because their areas are not volatile.

The suite as a whole is a [supporting/generic subdomain](https://coupling.dev/posts/dimensions-of-coupling/volatility/) (test automation is not the company's competitive core), and the team/deployment topology is a single repo with a single owner — so internal effective distance is uniformly low and stays low.

## Coupling Overview

| Integration | [Strength](https://coupling.dev/posts/dimensions-of-coupling/integration-strength/) | [Distance](https://coupling.dev/posts/dimensions-of-coupling/distance/) | [Volatility](https://coupling.dev/posts/dimensions-of-coupling/volatility/) | [Balanced?](https://coupling.dev/posts/core-concepts/balance/) |
| --- | --- | --- | --- | --- |
| Page objects (`OpenAccountPage`, `MainPage`) → Vivid frontend (SUT) | [Intrusive](https://coupling.dev/posts/dimensions-of-coupling/integration-strength/) | High — separate system, separate team, independent deploys, runtime/DOM | High | ❌ **No — unbalanced + volatile** |
| Test classes → page objects (fluent chains) | [Model](https://coupling.dev/posts/dimensions-of-coupling/integration-strength/) | Low — same module & team | Medium | ✅ Yes |
| `DriverSettings` / `DriverUtils` / `TestBase` → `Project.config` | [Functional](https://coupling.dev/posts/dimensions-of-coupling/integration-strength/) | Low | Low | ✅ Yes |
| `AllureAttachments` → `DriverUtils` → `Project.config` (video URL) | [Functional](https://coupling.dev/posts/dimensions-of-coupling/integration-strength/) | Low | Low | ⚠️ Tolerable (low cohesion) |
| `DriverUtils` internal grab-bag (5 unrelated statics) | n/a (cohesion) | Low | Low | ⚠️ [Low cohesion](https://coupling.dev/posts/core-concepts/balance/), tolerable |

## Issue: Intrusive coupling to the system under test

**Integration**: Page objects (`OpenAccountPage`, `MainPage`) → Vivid Money frontend
**Severity**: Critical

### Knowledge Leakage

The page objects encode two kinds of the frontend's *private* knowledge:

- **CSS-module hash selectors** — e.g. `.inviteForm__title__YudQx`, `.messageBlock__title__I9Ic_`, `.FormRow__errorBlock__oV8XY`. These hashes are build-time artifacts of the frontend's CSS-module tooling, not a published, stable contract.
- **Exact marketing copy as assertion oracles** — full paragraphs inlined as expectations: "Grow your money easily with commission-free and instant investing…", "Congrats 🎉", "Yay! The download link is on the way. Please check your phone".

This is [intrusive coupling](https://coupling.dev/posts/dimensions-of-coupling/integration-strength/): the test depends on the system under test's internal rendering details and exact content — knowledge the frontend never agreed to expose as an interface. Because the `check*` assertions live inside the page objects, the expected-content knowledge sits in the interaction layer too, making the leak implicit and easy to miss.

### Complexity Impact

The outcome of a frontend change is unpredictable for the test suite ([complexity](https://coupling.dev/posts/core-concepts/complexity/)). A frontend developer renaming a CSS module or a marketer rewording a headline has *no signal* that a test in a different repository depends on it. The dependency is invisible from the frontend side, so the change outcome surfaces only at the next test run as a red build — not at change time. Predicting "what breaks if the landing copy changes" requires holding the cross-system dependency in mind, which exceeds local working memory because nothing in the frontend codebase points back to these tests.

### Cascading Changes

- A frontend production build regenerates CSS-module hashes → every hashed selector silently stops matching → mass test failures with no corresponding test change.
- Marketing edits a headline, subtitle, or success message → `shouldHave(text(...))` assertions fail although behavior is correct.

These cascades are expensive precisely because the [distance](https://coupling.dev/posts/dimensions-of-coupling/distance/) is high: the trigger and the breakage live in different systems, owned by different teams, deployed on independent schedules. High [strength](https://coupling.dev/posts/dimensions-of-coupling/integration-strength/) × high distance × high [volatility](https://coupling.dev/posts/dimensions-of-coupling/volatility/) is the worst quadrant of the [balance rule](https://coupling.dev/posts/core-concepts/balance/).

### Recommended Improvement

Reduce **strength** from intrusive to [contract](https://coupling.dev/posts/dimensions-of-coupling/integration-strength/) — distance cannot and should not be reduced (you can't co-locate a test suite with a production frontend):

- Ask the frontend team to expose stable `data-testid` / `data-qa` attributes and select on those. This replaces private CSS hashes with a published-language contract: an explicit, stable interface both sides own.
- Minimize copy assertions: verify stable structural/semantic signals (element present, correct field, a key invariant) instead of full marketing paragraphs. Where exact copy must be checked, source it from a shared fixture both teams own rather than inlining it in the page object.

**Trade-off**: requires coordination with the frontend team and a small upfront cost (adding test ids). Worthwhile because it converts the worst-quadrant coupling into balanced contract coupling and removes the recurring "a frontend build broke the tests" cascade — the single largest source of flakiness in a suite like this.

## Issue: Low cohesion in DriverUtils

**Integration**: `DriverUtils` internals and its callers
**Severity**: Minor

### Knowledge Leakage

`DriverUtils` bundles five unrelated static helpers: session id, screenshot bytes, page-source bytes, video-URL building, and console logs. They share no state and only weakly share a theme — classic [low cohesion](https://coupling.dev/posts/core-concepts/balance/). Notably, `getVideoUrl` does not touch the WebDriver at all; it concatenates `Project.config.videoStorage()` with a session id, so it is video/config knowledge misfiled under a "driver" utility.

### Complexity Impact

Mild. A reader must scan five concerns to locate one, and the catch-all name "Utils" invites further unrelated additions — entropy toward a grab-bag over time.

### Cascading Changes

Minimal. The helpers don't share state, so changes rarely cascade. This is why the issue stays Minor despite the cohesion smell: the area is low-[volatility](https://coupling.dev/posts/dimensions-of-coupling/volatility/), and the [balance rule](https://coupling.dev/posts/core-concepts/balance/) tolerates an imbalance when volatility is low.

### Recommended Improvement

Optionally split by concern — most cleanly, move `getVideoUrl` next to the video-attachment logic, since it is video knowledge, not driver knowledge. Low priority; address opportunistically. Do not introduce interfaces — there is one implementation and no second consumer, so an abstraction would be premature.

## Issue: Three-hop video-URL chain

**Integration**: `AllureAttachments.addVideo` → `DriverUtils.getVideoUrl` → `Project.config.videoStorage()`
**Severity**: Minor

### Knowledge Leakage

A single URL concatenation is reached through three hops across two helper classes plus the global `Project.config` singleton. The indirection adds navigation cost without introducing a meaningful abstraction boundary.

### Complexity Impact

Low, but the chain spreads the "video" concern across three locations: understanding how the video URL is formed and attached requires hopping between files.

### Cascading Changes

Minimal; the area is low-volatility and all hops are at low [distance](https://coupling.dev/posts/dimensions-of-coupling/distance/) (same module, same team). Tolerable technical debt.

### Recommended Improvement

Co-locate the video concern — URL building, the readiness/retry wait, and the attach call — in one place, collapsing the chain. Low priority; best done together with the `DriverUtils` cohesion cleanup above.

---

_This analysis was performed using the [Balanced Coupling](https://coupling.dev) model by [Vlad Khononov](https://vladikk.com)._
