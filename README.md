# **Lekce 08: Síťová komunikace a Coroutines (Odesílání)**

V předchozích lekcích jsme vytvořili UI chatu a nastavení serveru. Teď je čas data skutečně odeslat "ven" z telefonu.

## **Cíl této lekce**

1. Pochopit, proč síťová komunikace nesmí běžet na hlavním vlákně.
2. Naučit se používat **Kotlin Coroutines** pro operace na pozadí.
3. Odeslat zprávu na TCP server.

## **Co se změnilo?**

* **`AndroidManifest.xml`**: Přidali jsme `<uses-permission android:name="android.permission.INTERNET" />`. Bez toho by aplikace spadla s chybou SecurityException.
* **`SecondActivity.kt`**:
    * Přidali jsme `CoroutineScope(Dispatchers.IO)`.
    * Tlačítko "Odeslat" už jen nepřidává zprávu do seznamu, ale otevírá **Socket** a posílá data.

## **Jak na to? (Test)**

Aby aplikace fungovala, potřebujete druhou stranu – TCP Server, který bude zprávy přijímat.

Návod na zprovoznění testovacího serveru a postup pro propojení telefonu přes Hotspot najdete v hlavním repozitáři, ze kterého jste byli na tento projekt přesměrováni.

## **Proč Coroutines?**

Android má pravidlo: **Hlavní vlákno (Main Thread) se stará o vykreslování UI.** Pokud byste na něm zkusili připojit k serveru (což může trvat 2 sekundy), aplikace by na 2 sekundy "zamrzla". Uživatel by nemohl na nic kliknout.

Proto používáme launch(Dispatchers.IO), což řekne Androidu: *"Tuhle těžkou práci udělej na vedlejším vlákně a neblokuj tlačítka."*