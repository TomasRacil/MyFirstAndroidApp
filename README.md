# **My First Android App - Referenční Repozitář**

Vítejte v repozitáři, který obsahuje kompletní zdrojový kód pro naši výukovou aplikaci. Tento projekt slouží jako praktická ukázka tvorby chatovací aplikace v Kotlinu, od základního UI až po síťovou komunikaci.

Tento repozitář je rozdělen do **větví (branches)**, které odpovídají jednotlivým fázím výuky. Díky tomu si můžete kdykoli stáhnout stav aplikace přesně tak, jak má vypadat na konci konkrétní lekce.

## **Jak tento repozitář používat**

### **1. Stažení repozitáře**

Nejprve si repozitář naklonujte k sobě do počítače:

```shell
git clone https://github.com/TomasRacil/MyFirstAndroidApp.git 
cd MyFirstAndroidApp
```

### **2. Přepínání mezi lekcemi**

Seznam všech větví zobrazíte příkazem:

```shell
git branch -a
```

Pokud chcete přeskočit na konkrétní fázi (například začátek práce s databází), přepněte se na příslušnou větev:


```shell
git checkout 04-database-start
```

*Pozor: Před přepnutím větve musíte mít uložené nebo zahozené změny ve své aktuální práci.*

---

## **Seznam větví (Osnova)**

Zde je přehled, co v které větvi najdete:

| Větev                  | Téma                 | Klíčové novinky                                                    |
|:-----------------------|:---------------------|:-------------------------------------------------------------------|
| **main**               | **Finální aplikace** | Kompletní hotový projekt se všemi funkcemi.                        |
| `01-starter`           | Start                | Čistý projekt, prázdná aktivita.                                   |
| `02-ui-layouts`        | UI                   | Hotový XML layout pro přihlašovací formulář.                       |
| `03-logic-basic`       | Logika               | Obsluha tlačítka, Toast zprávy.                                    |
| `04-lifecycle-intents` | Navigace             | Druhá obrazovka, Intent, start nové aktivity.                      |
| `05-lifecycle-fix`     | Architektura         | Implementace **ViewModel** pro zachování dat.                      |
| `06-recyclerview`      | Seznamy              | **RecyclerView**, Adapter Pattern a vlastní XML pro zprávy.        |
| `07-preferences`       | Nastavení            | Ukládání IP adresy a portu přes **SharedPreferences**.             |
| `08-networking-out`    | Síť (Odesílání)      | **Coroutines** (Dispatchers.IO), TCP Sockets, Internet Permission. |
| `09-networking-in`     | Síť (Příjem)         | Reálný čas, nekonečná smyčka ve ViewModelu a LiveData.             |

---

## **Klíčové koncepty v aplikaci**

### **1. Dynamické seznamy (RecyclerView)**

Místo zastaralého ListView používáme `RecyclerView`. Ten je efektivní díky **recyklaci** grafických prvků – na obrazovce jich existuje jen několik a při scrollování se do nich pouze "vévají" nová data pomocí `Adapteru`.

### **2. Architektura ViewModel & LiveData**

Aby data (jako seznam zpráv) nezmizela při otočení displeje, držíme je ve `ViewModelu`. Pomocí `LiveData` pak aktivita automaticky reaguje na změny – jakmile přijde nová zpráva ze sítě, UI se samo překreslí.

### **3. Vlákna a Coroutines**

Síťová komunikace (TCP) nesmí běžet na hlavním vlákně, jinak by aplikace zamrzla. Používáme:

* **Main Thread:** Pouze pro vykreslování UI.
* **IO Dispatcher:** Pro síťové operace a práci se soubory na pozadí.

---

## **Tipy pro studium**

* **Porovnávání změn:** Pokud nevíte, co se změnilo mezi lekcemi, použijte na GitHubu funkci **Compare** (např. porovnejte `06-recyclerview` s `07-preferences`).
* **Zprovoznění serveru:** Pro testování lekcí 08 a 09 potřebujete běžící TCP server. Návod k jeho spuštění naleznete v dokumentaci k výukovému modulu.
* **Zasekl jsem se:** Pokud vám kód nefunguje, přepněte se na větev následující lekce. Získáte tím funkční výchozí bod.

## **Otevření v Android Studiu**

1. V Android Studiu zvolte **File -> Open**.
2. Vyberte složku s tímto repozitářem.
3. Počkejte na dokončení **Gradle Sync** (může trvat několik minut při prvním spuštění).