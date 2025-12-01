# **My First Android App - Referenční Repozitář**

Vítejte v repozitáři, který obsahuje kompletní zdrojový kód pro naši výukovou aplikaci.

Tento repozitář je rozdělen do **větví (branches)**, které odpovídají jednotlivým fázím výuky. Díky tomu si můžete kdykoli stáhnout stav aplikace přesně tak, jak má vypadat na konci konkrétní lekce.

## **Jak tento repozitář používat**

### **1. Stažení repozitáře**

Nejprve si repozitář naklonujte k sobě do počítače:

```shell
git clone https://github.com/TomasRacil/MyFirstAndroidApp.git 
cd MyFirstAndroidApp
```

### **2. Přepínání mezi lekcemi**

Repozitář používá systém větví pro každou fázi. Seznam všech větví zobrazíte příkazem:

```shell
git branch -a
```

Pokud chcete přeskočit na konkrétní fázi (například začátek práce s databází), přepněte se na příslušnou větev:


```shell
git checkout 04-database-start
```

*Pozor: Před přepnutím větve musíte mít uložené nebo zahozené změny ve své aktuální práci.*

## **Seznam větví (Osnova)**

Zde je přehled, co v které větvi najdete:

| Větev | Popis | Odpovídající lekce |
| :---- | :---- | :---- |
| main | **Finální aplikace.** Kompletní hotový projekt se všemi funkcemi. | Konec kurzu |
| 01-starter | Start | Čistý projekt, prázdná aktivita. |
| 02-ui-layouts | UI | Hotový XML layout pro přihlašovací formulář. |
| 03-logic-basic | Logika | Obsluha tlačítka, Toast zprávy. |
| 04-lifecycle-intents | Navigace | Druhá obrazovka, Intent, start nové aktivity. |
| 05-lifecycle-fix | Architektura | Implementace **ViewModel** pro zachování dat. |


## **Tipy pro studium**

* **Porovnávání změn:** Pokud nevíte, co se změnilo mezi lekcí 2 a 3, můžete si na GitHubu/GitLabu otevřít **Compare** a porovnat větev `02-ui-layouts` s `03-logic-basic`. Uvidíte přesně ty řádky kódu, které jsme přidali.
* **Zasekl jsem se:** Pokud vám kód nefunguje a nemůžete přijít na to proč, přepněte se na větev následující lekce. Tím získáte funkční verzi a můžete pokračovat ve výuce.

## **Otevření v Android Studiu**

1. V Android Studiu zvolte **File -> Open**.
2. Vyberte složku s tímto repozitářem.
3. Počkejte na dokončení **Gradle Sync**.