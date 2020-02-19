# Prog2@UniMI Handouts

[![License: GPL v3](https://img.shields.io/badge/License-GPL%20v3-blue.svg)](http://www.gnu.org/licenses/gpl-3.0)
[![License: CC BY-SA 4.0](https://img.shields.io/badge/License-CC%20BY--SA%204.0-blue.svg)](http://creativecommons.org/licenses/by-sa/4.0/)

Questo repository contiene gli *handout* dell'insegnamento di [Programmazione II](https://prog2.di.unimi.it/) del corso di laurea in [Informatica](https://informatica.cdl.unimi.it/it) dell'[Università degli Studi di Milano](http://www.unimi.it/).

Il materiale di questo repository (aggiornato periodicamente) consiste nella
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

Una volta ottenuto e scompattato l'archivio, portatevi nella cartella che avrete
così ottenuto; per **compilare** i file usate il comando

    javac -encoding UTF-8 -d dist src/it/unimi/di/prog2/*/*.java

quindi per **eseguire** una classe invocate la JVM con il *classpath*
corrispondente a `dist`, la directory dove avete compilato i file. Ad esempio,
per eseguire la *classe* `ComputeFactorial` del *pacchetto*
`it.unimi.di.prog2.l04` usate il comando

    java -cp dist it.unimi.di.prog2.l04.ComputeFactorial

potete terminare l'esecuzione usando `^C` o `^D` (ossia premendo il tasto `ctrl`
assieme al tasto `c` o `d`). In fine, se volete **generare la documentazione**
in formato HTML usate il comando

    javadoc -d docs -private src/it/unimi/di/prog2/*/*.java

e quindi aprite nel *browser* il documento `docs/index.html`; è disponibile una
[versione precompilata della documentazione](https://prog2-unimi.github.io/handouts/).

### Usare uno strumento di build auotmation

Per automatizzare i compiti sopra illustrati, ma sottolineando che *non è
assolutamente materia dell'insegnamento* e **non è assolutamente richiesto per
il superamento dell'esame**, gli studenti interessati possono usare uno
strumento di [build automation](https://en.wikipedia.org/wiki/Build_automation),
come quello mostrato del [repository
d'esempio](https://github.com/prog2-unimi/build-automation-example); in tal
caso, però, si osservi che sarà  necessario *ristrutturare* la directory `src`
contenuta in questo repository secondo l'organizzazione richiesta dal tool (e
mostrata nel repository d'esempio).
