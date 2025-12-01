# **📱 Lekce 03: Logika a Interaktivita**

Máme hezké tlačítko, ale nic nedělá. V této lekci to změníme.

Tato větev (`03-logic-basic`) propojuje náš XML vzhled s Kotlin kódem.

## **Cíl této lekce**

Pochopit, jak v kódu (Kotlin) najít prvky, které jsme vytvořili v grafice (XML), a jak reagovat na akce uživatele.

## **Co se změnilo?**

Pracujeme hlavně v souboru:

* **`app/src/main/java/.../MainActivity.kt`**

### **Klíčové koncepty v kódu:**

1. **`findViewById<Typ>(R.id.id_prvku)`**:
    * Tímto příkazem říkáme: *"Najdi mi v paměti to tlačítko, které jsem v XML pojmenoval `btnLogin`."*
    * Uložíme si ho do proměnné, abychom s ním mohli pracovat.
2. **`setOnClickListener { ... }`**:
    * Tady definujeme, co se má stát po kliknutí. Všechno uvnitř složených závorek `{}` se provede až ve chvíli, kdy uživatel klepne na displej.
3. **`text.toString()`**:
    * Získání obsahu textového pole.
4. **`Toast.makeText(...).show()`**:
    * Malá vyskakovací bublina (zpráva) dole na obrazovce. Ideální pro rychlou zpětnou vazbu.

## **Jak na to?**

1. Spusťte aplikaci na telefonu/emulátoru.
2. Zkuste kliknout na "Přihlásit se" bez vyplnění údajů -> Měl by se zobrazit Toast s chybou.
3. Vyplňte jméno a heslo -> Měl by vás pozdravit Toast se jménem.

## **Úkol k zamyšlení**

Podívejte se do kódu `MainActivity.kt`.  
Jak byste upravili podmínku `if`, aby aplikace vyžadovala, že heslo musí být delší než 5 znaků?  
*(Nápověda: String má vlastnost `.length`)*

*Gratuluji! Máte svou první interaktivní aplikaci.*