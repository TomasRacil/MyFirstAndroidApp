# **Lekce 04: Životní cyklus a Intenty**

Vítejte u klíčové lekce. Aplikace s jednou obrazovkou jsou nudné. Dnes se naučíme přecházet mezi obrazovkami a posílat si data.

## **Cíl této lekce**

1. Vytvořit **druhou aktivitu** (`SecondActivity`).
2. Pochopit **Intent** (Záměr) - zpráva, která systému říká "Chci spustit něco dalšího".
3. Poslat data (jméno uživatele) z první aktivity do druhé.

## **Co se změnilo?**

* **`SecondActivity.kt` + `activity_second.xml`**: Nová obrazovka s uvítáním.
* **`AndroidManifest.xml`**: Zaregistrovali jsme novou aktivitu.
* **`MainActivity.kt`**:
    * Místo `Toast` zprávy vytváříme `Intent`.
    * `putExtra("USER_NAME", username)`: Balíme data do nové aktivity.
    * `startActivity(intent)`: Spustíme novou aktivitu.

## **Jak na to?**

1. Spusťte aplikaci.
2. Zadejte jméno a heslo.
3. Klikněte na **Přihlásit se**.
4. Měli byste se ocitnout na nové obrazovce, která vás pozdraví jménem.
5. Tlačítkem **Zpět** se vrátíte na přihlášení (metoda `finish()`).

## **Úkol k zamyšlení (Životní cyklus)**

Zkuste na přihlašovací obrazovce napsat jméno, ale neklikejte na Přihlásit.  
Místo toho otočte telefon na šířku (v emulátoru tlačítko Rotate).  
Co se stalo s textem? Zmizel?  
Proč? Protože při otočení se aktivita zničí (`onDestroy`) a vytvoří znovu (`onCreate`), aby se přizpůsobila novému rozměru displeje. Proměnné se vymažou. To je základní problém životního cyklu, který budeme řešit později (pomocí `ViewModel` nebo `onSaveInstanceState`).