# Prog2@UniMI Handouts

[![License: GPL v3](https://img.shields.io/badge/License-GPL%20v3-blue.svg)](http://www.gnu.org/licenses/gpl-3.0)
[![License: CC BY-SA 4.0](https://img.shields.io/badge/License-CC%20BY--SA%204.0-blue.svg)](http://creativecommons.org/licenses/by-sa/4.0/)

Questo repository contiene gli *handout* dell'insegnamento di [Programmazione II](https://prog2.di.unimi.it/) del corso di laurea in [Informatica](https://informatica.cdl.unimi.it/it) dell'[Università degli Studi di Milano](http://www.unimi.it/).

Il materiale di questo repository (aggiornato periodicamente) consisterà nella
collezione del *materiale didattico* del docente sostanzialmente nella forma in
cui si trova al termine di ciascuna lezione. Per questa ragione il suo contenuto
**non è assolutamente inteso come sostituivo dei libri di testo e della
documentazione suggerita** e in nessun caso è da ritenersi *esaustivo* e *privo
di errori*, ma **è provvisto al solo scopo di consentire agli studenti di
ripercorrere i passi visti a lezione** (poiché i *sorgenti* ed i *notebook* sono
nella maggior parte dei casi *privi di commenti* e *non sono corredati da alcun
testo esplicativo*, risulteranno probabilmente incomprensibili per chi non ha
frequentato le lezioni).

## Come ottenere ed utilizzare i sorgenti sul proprio computer

Potete scaricare un [archivio
zip](https://github.com/prog2-unimi/handouts/archive/master.zip) del contenuto
di questo repository usando il link in questa frase, oppure il bottone verde
"Clone or download" in altro a destra nella pagina dove state leggendo questo
`README.md`.

Una volta ottenuta una copia locale, a patto che abbia installato il *Java
Developer Kit* versione 17, può procedere a *compilare* il codice con il
comando:

    ./gradlew build

(che provvederà anche ad eseguire tutti i *test* specificati nella directory
`tests`); quindi potrà eseguire il codice di una specifica classe, ad esempio
`it.unimi.di.prog2.l01.SalveMondo` con il comando

    ./gradlew runClass -PmainClass=it.unimi.di.prog2.l01.SalveMondo

Maggiori informazioni sul *build automation tool* sono disponibili nell'apposito
[repository d'esempio](https://github.com/prog2-unimi/build-automation-example).

È possibile accedere direttamente alla [documentazione
Javadoc](https://prog2-unimi.github.io/handouts/) generata a partire dai
sorgenti di questo repository.

## Il materiale degli scorsi anni accademici

Gli studenti che hanno frequentato nei precedenti anno accademici possono
trovare il materiale nel

* branch dell'[AA 2019/20](../../tree/aa1920),
* branch dell'[AA 2020/21](../../tree/aa2021),
* branch dell'[AA 2021/22](../../tree/aa2122),
* branch dell'[AA 2022/23](../../tree/aa2223).