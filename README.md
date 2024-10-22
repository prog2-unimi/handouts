# Prog2@UniMI Handouts

[![License: GPL v3](https://img.shields.io/badge/License-GPL%20v3-blue.svg)](http://www.gnu.org/licenses/gpl-3.0)
[![License: CC BY-SA 4.0](https://img.shields.io/badge/License-CC%20BY--SA%204.0-blue.svg)](http://creativecommons.org/licenses/by-sa/4.0/)

Questo repository contiene gli *handout* dell'insegnamento di [Programmazione
II](https://prog2.di.unimi.it/) del corso di laurea in
[Informatica](https://informatica.cdl.unimi.it/it) dell'[Università degli Studi
di Milano](http://www.unimi.it/).

Il materiale di questo repository (aggiornato periodicamente) consiste nella
collezione del *materiale didattico* prodotto dal docente sostanzialmente nella
forma in cui si trova al termine di ciascuna lezione. Per questa ragione il suo
contenuto **non è assolutamente inteso come sostituivo dei libri di testo e
della documentazione suggerita** e in nessun caso è da ritenersi *esaustivo* e
*privo di errori*, ma **è provvisto al solo scopo di consentire agli studenti di
ripercorrere i passi visti a lezione** e di eseguire **alcuni esercizi**.

## Come ottenere ed utilizzare questo materiale sul proprio computer

Può scaricare un [archivio
zip](https://github.com/prog2-unimi/handouts/archive/master.zip) del contenuto
di questo repository usando il link in questa frase, oppure il bottone verde
"Clone or download" in altro a destra nella pagina dove sta leggendo questo
`README.md`.

Al fine di consentire l'automazione di alcuni compiti, tra i quali la
**compilazione**, l'**esecuzione dei test** *black-box*  la **generazione della
documentazione**, questo materiale fa uso di un *build automation tool*
denominato [Gradle](https://gradle.org/) unitamente al *testing framework*
[Jubbiot](https://github.com/prog2-unimi/jubbiot) basato su
[JUnit](https://junit.org/junit5/). Una *conoscenza approfondita del
funzionamento di tali strumenti non è affatto necessaria per lo svolgimento
degli esercizi, o per il superamento dell'esame*, perché sono qui completamente
configurati e predisposti dal docente.

### Come compilare ed eseguire i test e il codice

Una volta ottenuta una copia locale, a patto che abbia installato il *Java
Developer Kit* versione 21, può procedere a **compilare** il codice con il
comando:

    ./gradlew build

se usa una ragionevole versione di GNU/Linux, oppure, se usa Windows:

    gradlew.bat build

questo comando provvederà anche ad eseguire tutti i **test** specificati nella
directory `tests`.

Può eseguire il codice di una specifica classe, ad esempio
`it.unimi.di.prog2.l01.SalveMondo` con il comando

    ./gradlew runClass -PmainClass=it.unimi.di.prog2.l01.SalveMondo

Per maggiori informazioni sulle modalità di testing consulti l'[esempio d'uso di
Jubbiot](https://github.com/prog2-unimi/jubbiot/blob/master/README.md#example);
in particolare presti attenzione alla sezione sulla [generazione dell'output
dello studente](https://github.com/prog2-unimi/jubbiot/blob/master/README.md#generating-actual-outputs).

### Come generare la documentazione

Può generare la documentazione con il comando:

    ./gradlew javadoc

tale comando è configurato per riportare un errore in caso di *warning*, al fine
di aiutarla nel comprendere se la documentazione è, almeno dal punto di vista
sintattico, completa.

### Uso diretto del JDK

Chi non intendesse usare Gradle può comunque compilare ed eseguire il codice,
nonché generare la documentazione (ad esempio con un IDE di sua scelta). Osservi
però che *nel caso del progetto* non sarà valutato alcun codice che presenti
*errori* (o *warning*) di compilazione (sia nella parte di *Java* che di
*Javadoc*).

Per questa ragione si consiglia, qualora si decida di usare gli usuali comandi
del JDK, di aggiungere sempre le opzioni `-Xlint:all -Werror` al comando `javac`
e `-Xdoclint:all -Werror` al comando `javadoc` (o di configurare analogamente il
proprio IDE).

## Il materiale degli scorsi anni accademici

Gli studenti che hanno frequentato nei precedenti anno accademici possono
trovare il materiale nel

* branch dell'[AA 2019/20](../../tree/aa1920),
* branch dell'[AA 2020/21](../../tree/aa2021),
* branch dell'[AA 2021/22](../../tree/aa2122),
* branch dell'[AA 2022/23](../../tree/aa2223),
* branch dell'[AA 2023/24](../../tree/aa2324).