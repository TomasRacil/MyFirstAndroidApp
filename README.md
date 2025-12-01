# **Lekce 05: Řešení životního cyklu (ViewModel)**

V minulé lekci jsme zjistili nepříjemnou věc: když otočíte telefon, data z formuláře zmizí. Proč? Protože aktivita se zničí a vytvoří znovu.

Dnes to opravíme pomocí **ViewModelu**.

## **Cíl této lekce**

1. Pochopit, co je to **ViewModel**.
2. Naučit se oddělovat data (State) od zobrazení (UI).
3. Zajistit, aby data "přežila" rotaci displeje.

## **Co se změnilo?**

* **`build.gradle`**: Přidali jsme knihovny pro `lifecycle` a `viewmodel`.
* **`MainViewModel.kt`**: Nová třída. Je to "trezor" na data. Android garantuje, že tento objekt nezničí při rotaci displeje.
* **`MainActivity.kt`**:
    * Už si nepamatuje data sama.
    * Ptá se ViewModelu: *"Máš pro mě něco uloženého?"*
    * Používá delegáta `by viewModels()`.

## **Jak na to? (Test)**

1. Spusťte aplikaci.
2. Napište do jména "Petr".
3. **Otočte telefon** (Rotate).
4. **Sledujte zázrak:** Text "Petr" tam zůstal! 🎉
    * *(V minulé lekci by zmizel).*

## **Proč to tak funguje?**

ViewModel "žije" déle než Aktivita.

1. Aktivita vznikne -> Vytvoří se ViewModel.
2. Aktivita se otočí (zanikne) -> ViewModel **stále "žije"**.
3. Nová Aktivita vznikne -> Připojí se ke **stejnému** ViewModelu a vezme si z něj data.
4. Aktivita se definitivně ukončí (tlačítko Zpět) -> Teprve teď zanikne i ViewModel.