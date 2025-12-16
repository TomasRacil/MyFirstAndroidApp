# **Lekce 06: UI Chatu a Seznamy (RecyclerView)**

V minulé lekci jsme se naučili posílat data (jméno uživatele) z jedné aktivity do druhé. Nyní ale chceme víc než jen statický text "Vítejte". Chceme dynamický chat, kde přibývají zprávy.

Dnes se naučíme jednu z nejdůležitějších komponent v Androidu: **RecyclerView**.

## **Cíl této lekce**

1. Pochopit, jak zobrazovat dlouhé seznamy dat pomocí **RecyclerView**.
2. Naučit se **Adapter Pattern** – jak propojit čistá data (Kotlin List) s grafikou (XML).
3. Vytvořit vlastní vzhled pro položku seznamu (chatovací bublinu).

## **Co se změnilo?**

* **`Message.kt`**: Nová datová třída. Je to jen "přepravka" na data (obsahuje text, odesílatele a čas).
* **`item_message.xml`**: Nový layout. Definuje, jak vypadá **jedna** zpráva (bublina + jméno).
* **`MessageAdapter.kt`**: Nová třída. Je to "manažer", který bere data ze seznamu a sype je do `item_message.xml`.
* **`activity_second.xml`**:
    * Smazali jsme statický text.
    * Přidali jsme `<RecyclerView>` (prostor pro seznam).
    * Přidali jsme `<EditText>` a `<Button>` pro psaní zpráv.
* **`SecondActivity.kt`**:
    * Už jen nevypisuje jméno.
    * Obsluhuje tlačítko "Odeslat".
    * Říká adaptéru: *"Hej, mám novou zprávu, překresli se!"*

## **Jak na to? (Test)**

1. Spusťte aplikaci a přihlašte se svým jménem (např. "Tomáš").
2. Otevře se obrazovka chatu (SecondActivity).
3. Do spodního pole napište "Ahoj světe".
4. Klikněte na **Odeslat**.
5. **Sledujte výsledek:** Zpráva se okamžitě objeví v seznamu nahoře. Můžete přidávat další a seznam bude rolovat.

## **Proč to tak funguje?**

Starší Android používal `ListView`, který byl pomalý. My používáme **RecyclerView**.

1. **Efektivita:** Když máte v chatu 1000 zpráv, telefon nevytvoří 1000 grafických prvků (to by ho zabilo). Vytvoří jich jen tolik, kolik se vejde na displej (např. 10).
2. **Recyklace:** Když odscrolujete zprávu nahoru pryč z obrazovky, Android ten grafický prvek nezničí. Vezme ho, vymaže starý text, dá do něj nový text (zprávu, která právě přijíždí zespodu) a použije ho znovu.
3. **Adapter:** Funguje jako most. Na jedné straně má `List<Message>` (data), na druhé straně `RecyclerView` (grafika). Jeho úkolem je lepit data do grafických šablon.