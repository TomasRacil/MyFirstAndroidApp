# **Lekce 01: Startovní bod**

Vítejte na startovní čáře vaší první Android aplikace!

Tato větev (`01-starter`) obsahuje čistý projekt vygenerovaný Android Studiem. Není v něm žádná naše vlastní logika ani složité úpravy.

## **Cíl této lekce**

V této fázi se seznámíte s prostředím a strukturou projektu. Vaším úkolem je:

1. **Otevřít projekt** v Android Studiu.
2. **Pochopit strukturu** (`manifest`, `res`, `java`).
3. **Spustit aplikaci** na emulátoru nebo fyzickém telefonu.
4. **Udělat drobné změny** v XML layoutu (změnit text "Hello World").

## **Struktura souborů**

Zde je rychlý přehled toho, co vidíte:

* **`app/src/main/AndroidManifest.xml`**: "ID" aplikace. Definuje název, ikonku a aktivity.
* **`app/src/main/java/.../MainActivity.kt`**: Hlavní vstupní bod. Zatím jen načítá layout.
* **`app/src/main/res/layout/activity_main.xml`**: Vzhled obrazovky. Zde budete měnit texty a tlačítka.
* **`build.gradle`**: Konfigurace sestavení (verze SDK, knihovny).

## **Jak začít?**

1. Ujistěte se, že máte načtený projekt (Gradle Sync finished).
2. Otevřete `activity_main.xml`.
3. Zkuste přepsat text v `<TextView>` a spusťte aplikaci znovu.

*Pokud se něco pokazí, můžete se vždy vrátit do tohoto čistého stavu příkazem `git checkout 01-starter`.*