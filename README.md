# **Lekce 09: Příjem zpráv a ViewModel**

Náš chat už umí odesílat zprávy, ale stále nevíme, jestli někdo odpovídá. Dnes naučíme aplikaci **naslouchat**.

## **Cíl této lekce**

1. Přesunout síťovou logiku do **ViewModelu** (aby spojení přežilo otočení displeje).
2. Spustit nekonečnou smyčku pro čtení zpráv na pozadí.
3. Aktualizovat seznam zpráv v reálném čase, když někdo napíše.

## **Co se změnilo?**

* **ChatViewModel.kt**: Nová třída. Zde se odehrává veškerá magie sítě.
    * Drží otevřený Socket.
    * Má smyčku while(true), která čte řádky ze serveru.
    * Používá LiveData, aby dala vědět UI: *"Mám novou zprávu\!"*
* **SecondActivity.kt**:
    * Už se nestará o síť přímo.
    * Jen sleduje ChatViewModel a překresluje seznam.

## **Jak testovat?**

Aby aplikace fungovala, potřebujete druhou stranu – TCP Server, který bude zprávy přijímat.

Návod na zprovoznění testovacího serveru a postup pro propojení telefonu přes Hotspot najdete v hlavním repozitáři, ze kterého jste byli na tento projekt přesměrováni.