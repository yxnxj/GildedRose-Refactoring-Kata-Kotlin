# Gilded Rose 리팩토링 시작 가이드

이 프로젝트의 목표는 기존 동작을 보존하면서 코드를 더 읽기 쉽고 변경하기 쉬운 구조로 바꾸고, 마지막에 `Conjured` 아이템 규칙을 추가하는 것입니다.

## 먼저 읽을 문서

- `docs/GildedRoseRequirements_kr.md`: 한국어 요구사항
- `docs/GildedRoseRequirements.md`: 원본 영어 요구사항
- `docs/KataREADME_original.md`: 카타 전체 안내 원문

## 핵심 제약

- `Item` 클래스는 변경하지 않습니다.
- `GildedRose.items` 속성은 변경하지 않습니다.
- 기존 아이템의 동작은 리팩토링 전후로 같아야 합니다.
- `Conjured` 규칙은 리팩토링 후 추가하는 편이 안전합니다.

## 추천 진행 순서

1. 현재 상태 확인

   ```bash
   ./gradlew -q texttest
   ```

   이 출력은 현재 레거시 코드의 동작을 보여주는 기준입니다. 리팩토링 중에는 이 출력이 바뀌지 않아야 합니다.

2. 실패하는 스타터 테스트 정리

   `src/test/kotlin/com/gildedrose/GildedRoseTest.kt`의 기본 테스트는 일부러 실패하도록 만들어진 시작점입니다. 요구사항을 보고 의미 있는 테스트로 바꿉니다.

3. 작은 단위로 리팩토링

   처음부터 전체를 다시 작성하지 말고, `src/main/kotlin/com/gildedrose/GildedRose.kt`의 `updateQuality()`를 작게 나눕니다.

   추천 순서:

   - 아이템 이름 판별 함수 추출
   - 품질 증가/감소 함수 추출
   - `Sulfuras`, `Aged Brie`, `Backstage passes`, 일반 아이템 처리 분리
   - 중복 조건 제거
   - 테스트 실행

4. 자주 테스트 실행

   ```bash
   ./gradlew test
   ./gradlew -q texttest
   ```

   단위 테스트는 구체적인 규칙을 확인하고, 텍스트 출력은 전체 동작이 유지되는지 확인하는 용도로 씁니다.

5. 마지막에 `Conjured` 구현

   요구사항:

   - `Conjured` 아이템은 일반 아이템보다 `Quality`가 2배 빨리 감소합니다.
   - `Quality`는 음수가 될 수 없습니다.
   - 판매 기한이 지나면 일반 아이템처럼 감소 속도가 다시 커지는지 요구사항에 맞춰 테스트로 고정합니다.

## 리팩토링 팁

- 한 번에 하나의 의도만 바꾸고 테스트합니다.
- 동작 변경과 구조 변경을 같은 커밋에 섞지 않는 편이 좋습니다.
- 이름 문자열 비교가 여러 곳에 흩어지면 상수나 판별 함수로 모읍니다.
- `updateQuality()`가 읽기 편해졌을 때 새 기능을 추가합니다.

## 완료 기준

- `./gradlew test`가 통과합니다.
- `./gradlew -q texttest`가 정상 실행됩니다.
- 기존 아이템 규칙이 유지됩니다.
- `Conjured` 규칙을 설명하는 테스트가 있습니다.
