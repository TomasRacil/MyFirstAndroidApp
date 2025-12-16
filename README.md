# **Lekce 07: Ukládání dat (SharedPreferences)**

V minulé lekci jsme vytvořili vzhled chatu. Ale aby chat fungoval po síti, musí vědět, **kam** se připojit (IP adresa a port serveru). Tato data si musí pamatovat i po vypnutí aplikace.

Dnes se naučíme ukládat malá data trvale do paměti telefonu.

## **Cíl této lekce**

1. Vytvořit novou obrazovku **Nastavení**.
2. Pochopit rozdíl mezi `Intent` (posílání dat teď) a `SharedPreferences` (ukládání dat navždy).
3. Implementovat tlačítko v menu nebo na obrazovce pro přechod do nastavení.

## **Co se změnilo?**

* **`SettingsActivity.kt`**: Nová obrazovka. Obsahuje dvě políčka (IP a Port) a tlačítko Uložit.
* **`activity_settings.xml`**: Vzhled nové obrazovky.
* **`MainActivity.kt`**: Přidali jsme ikonku/tlačítko pro otevření nastavení.

## **Jak na to? (Test)**

1. Na hlavní obrazovce klikněte na nové tlačítko **Nastavení** (ozubené kolo).
2. Zadejte IP adresu (např. `10.0.2.2` pro localhost emulátoru) a port (např. `3000`).
3. Klikněte na **Uložit**.
4. Vypněte a zapněte aplikaci.
5. Jděte znovu do nastavení -> **Hodnoty tam stále jsou!** 🎉

## **Proč to tak funguje?**

**SharedPreferences** je jako malý notýsek, který má aplikace schovaný v telefonu. Zapisuje si do něj dvojice *Klíč-Hodnota*.

* **Zápis:** Otevřu notýsek (`edit()`), napíšu "IP" = "10.0.0.1", zavřu a uložím (`apply()`).
* **Čtení:** Otevřu notýsek, podívám se, co je u "IP". Pokud nic, použiji výchozí hodnotu.