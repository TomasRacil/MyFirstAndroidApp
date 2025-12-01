# **Lekce 02: Tvorba UI (XML Layouts)**

V této fázi opouštíme prázdnou obrazovku a vytváříme skutečné uživatelské rozhraní.

Tato větev (`02-ui-layouts`) obsahuje hotový XML kód pro přihlašovací formulář.

## **Cíl této lekce**

Naučit se pracovat s **XML Layouty** a poskládat prvky na obrazovku tak, aby to vypadalo dobře na různých telefonech.

Používáme **ConstraintLayout**, což je moderní způsob, jak definovat vztahy mezi prvky (např. "Tlačítko je pod Heslem").

## **Co se změnilo?**

Hlavní změny proběhly v souboru:

* **app/src/main/res/layout/activity_main.xml**

Přidali jsme tyto prvky:

1. `<TextView>`: Nadpis "Vítejte!".
2. `<EditText>` (id: `etUsername`): Pole pro jméno.
3. `<EditText>` (id: `etPassword`): Pole pro heslo (všimněte si `inputType="textPassword"`).
4. `<Button>` (id: `btnLogin`): Tlačítko pro odeslání.

## **Jak na to?**

1. Přepněte se do této větve: `git checkout 02-ui-layouts`.
2. Otevřete soubor `activity_main.xml`.
3. Vpravo nahoře přepněte zobrazení na **Split** (uvidíte kód i náhled).
4. Zkuste kliknout na nějaký prvek v náhledu – v kódu se vám zvýrazní.
5. Zkuste změnit text tlačítka nebo barvu nadpisu.

## **Tip**

Všimněte si atributů `app:layout_constraintTop_toBottomOf="..."`. Tyto řádky drží layout pohromadě. Kdybyste je smazali, všechny prvky by se "sesypaly" do levého horního rohu.

*Až budete mít UI prozkoumané, můžeme se vrhnout na oživení tlačítka v další lekci.*