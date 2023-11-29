/**
 * Codice relativo alla lezione 18 per maggiori dettagli si veda il <a
 * href="https://prog2.di.unimi.it/diario">diario del corso</a>.
 *
 * <p><em>Questo esercizio è stato assegnato come <strong>tema d’esame</strong> nell’appello del 21
 * gennaio 2020 ed è ispirato a <strong><a href="https://adventofcode.com/2019/day/12">The N-Body
 * Problem</a></strong>, il dodicesimo problema dell'<a href="https://adventofcode.com/">Advent of
 * Code</a> del 2019.</em>
 *
 * <h2>Sistema astronomico</h2>
 *
 * <p>Scopo dell’esercizio è progettare e implementare una gerarchia di oggetti utili a
 * rappresentare e simulare il comportamento di un <em>sistema astronomico</em> composto di alcuni
 * <em>corpi celesti</em> (come <em>pianeti</em> e <em>stelle fisse</em>) soggetti alla reciproca
 * interazione gravitazionale.
 *
 * <p>Dovrete decidere quali classi (concrete, o astratte) e quali interfacce implementare. Per
 * ciascuna di esse dovrete descrivere (preferibilmente in formato Javadoc, ma comunque attraverso
 * commenti presenti nel codice) le scelte relative alla rappresentazione dello stato (con
 * particolare riferimento all’<em>invariante di rappresentazione</em> e alla <em>funzione di
 * astrazione</em>) e ai metodi (con particolare riferimento a
 * <em>pre-</em>/<em>post-condizioni</em> ed <em>effetti collaterali</em>).
 *
 * <p>Un <strong>corpo celeste</strong> è caratterizzato da un <em>nome</em> e da una
 * <em>posizione</em>, descritta da un <strong>punto tridimensionale</strong> a coordinate intere;
 * per <em>norma</em> di un punto tridimensionale si intende la somma dei valori assoluti delle sue
 * componenti (altrimenti detta norma <span class="math inline">ℓ<sub>1</sub></span>). Assumeremo
 * per semplicità che ci siano solo due tipi di corpi celesti: le <strong>stelle fisse</strong> e i
 * <strong>pianeti</strong>. Le stelle non cambiano mai la loro posizione, a differenza dei pianeti.
 * Per questo, oltre che dalla posizione, sono caratterizzati dalla loro <em>velocità</em>,
 * descritta anch’essa da un punto tridimensionale. Ciascun corpo celeste ha una <em>energia</em>
 * data dal prodotto dell’<em>energia potenziale</em>, corrispondente alla norma della sua
 * posizione, per l’<em>energia cinetica</em>, corrispondente dalla norma della sua velocità
 * (ovviamente tale energia è nulla nel caso delle stelle fisse).
 *
 * <p>I corpi celesti sono soggetti all’<em>attrazione gravitazionale</em> reciproca che ne modifica
 * la velocità e, indirettamente, la posizione come segue:
 *
 * <ul>
 *   <li>
 *       <p>dapprima ciascun pianeta <em>modifica la sua velocità</em> in funzione dell’attrazione
 *       verso tutti gli altri corpi celesti: dato il pianeta <code>p</code> e il corpo celeste
 *       <code>c</code>, in maniera indipendente per ognuna delle tre coordinate, la velocità di
 *       <code>p</code> cambia di +1 o -1 a seconda che tale coordinata sia, rispettivamente, minore
 *       o maggiore di quella di <code>c</code>;
 *   <li>
 *       <p>una volta calcolata la nuova velocità per tutti i pianeti, ciascun pianeta <em>modifica
 *       la sua posizione</em> aggiungendo il valore della sua velocità a quello della sua posizione
 *       (come se per una unità di tempo il pianeta fosse soggetto a moto uniforme).
 * </ul>
 *
 * <p>Per esempio, se il sistema comprendesse due soli pianeti e inizialmente la coordinata <code>x
 * </code> della posizione di Marte fosse 3 e quella di Giove fosse 5, allora la coordinata <code>x
 * </code> della velocità di Marte cambierebbe di +1 (perché 3 &lt; 5) mentre quella di Giove
 * cambierebbe di -1 (perché 5 &gt; 3). Dato che all’inizio le velocità sono nulle, dopo tale
 * aggiornamento la coordinata <code>x</code> della velocità di Marte sarebbe 1, mentre quella di
 * Giove sarebbe -1 e l’aggiornamento della posizione dei due pianeti porterebbe la coordinata
 * <code>x</code> della posizione di entrambi a 4. Di conseguenza, all’aggiornamento successivo le
 * loro due velocità (ma non le loro posizioni) rimarrebbero immutate.
 *
 * <p>Un <strong>sistema astronomico</strong> è una collezione di pianeti e stelle fisse. Esso è
 * caratterizzato da uno <em>stato</em> che evolve a <em>tempo discreto</em>: al tempo 0 lo stato è
 * dato dall’elenco di tutti i corpi celesti che lo compongono, con la posizione assegnata e la
 * velocità nulla, quindi per ogni istante di tempo avvengono i due aggiornamenti descritti prima:
 *
 * <ul>
 *   <li>ciascun pianeta (interagendo con tutti i corpi celesti del sistema) aggiorna la sua
 *       velocità,
 *   <li>dopo che le nuove velocità sono state calcolate per tutti i pianeti, ciascun pianeta
 *       procede con l’aggiornare la sua posizione.
 * </ul>
 *
 * <p>Questo determina il nuovo stato, costituito dall’elenco di tutti i corpi celesti con le
 * posizioni e velocità opportunamente aggiornate. L’<em>energia totale</em> di un sistema
 * astronomico in un certo stato è data dalla somma dell’energia di tutti i corpi celesti di cui è
 * costituito.
 *
 * <h2 id="classe-di-test">Classe di test</h2>
 *
 * <p>Scrivete un metodo statico <code>main</code> nella classe che ritenete più opportuna che legga
 * dal flusso di ingresso una sequenza di quintuple corrispondenti ai vari corpi celesti; ogni
 * quintupla è data da:
 *
 * <ul>
 *   <li>un carattere che può essere <code>S</code> o <code>P</code> per indicare, rispettivamente,
 *       che il corpo celeste sia una stella o un pianeta,
 *   <li>una stringa (che non contiene spazi) che indica il nome del corpo celeste,
 *   <li>tre interi che indicano le coordinate iniziali del corpo celeste.
 * </ul>
 *
 * <p>Tali quintuple vanno utilizzate per popolare un sistema astronomico che deve essere fatto
 * evolvere un numero di passi pari all’intero indicato come parametro sulla linea di comando. Al
 * termine dell’evoluzione, dev’essere stampato l’elenco dei corpi celesti del sistema (in ordine
 * alfabetico di nome e con i dati relativi a posizione e velocità), infine deve essere stampata
 * l’energia totale del sistema.
 *
 * <h2 id="vincoli">Vincoli</h2>
 *
 * <p>Potete assumere che l’input abbia il formato specificato, che tutti i numeri coinvolti
 * nell’esecuzione del codice siano interi (e possano essere rappresentati da variabili di tipo
 * <code>int</code> per quanto concerne posizione e velocità, e di tipo <code>long</code> per quanto
 * concerne le energie). Pertanto, un modo plausibile di leggere le informazioni fornite in input è
 * il seguente:
 *
 * <pre><code>
 * Scanner s = new Scanner(System.in);
 * while (s.hasNext()) {
 * char pOrS = s.next().charAt(0); // can be P or S
 *   String name = s.next()
 *   int x = s.nextInt();
 *   int y = s.nextInt();
 *   int z = s.nextInt();
 *   …
 * }
 * </code></pre>
 *
 * <h2 id="esempio">Esempio</h2>
 *
 * <p>Eseguendo <code>soluzione 1</code> e avendo
 *
 * <pre><code>
 * P Marte -8 -10 0
 * P Giove 5 5 10
 * P Saturno 2 -7 3
 * P Venere 9 -8 -3
 * </code></pre>
 *
 * <p>nel flusso di ingresso, il programma emette nel flusso d’uscita
 *
 * <pre><code>
 * Pianeta, nome: Giove, pos: (4, 2, 7), vel: (-1, -3, -3)
 * Pianeta, nome: Marte, pos: (-5, -7, 1), vel: (3, 3, 1)
 * Pianeta, nome: Saturno, pos: (3, -8, 2), vel: (1, -1, -1)
 * Pianeta, nome: Venere, pos: (6, -7, 0), vel: (-3, 1, 3)
 * Energia totale: 312
 * </code></pre>
 *
 * <p>Similmente, col medesimo input, eseguendo <code>soluzione 100</code> viene emesso
 *
 * <pre><code>
 * Pianeta, nome: Giove, pos: (13, 16, -3), vel: (3, -11, -5)
 * Pianeta, nome: Marte, pos: (8, -12, -9), vel: (-7, 3, 0)
 * Pianeta, nome: Saturno, pos: (-29, -11, -1), vel: (-3, 7, 4)
 * Pianeta, nome: Venere, pos: (16, -13, 23), vel: (7, 1, 1)
 * Energia totale: 1940
 * </code></pre>
 *
 * <p>nel flusso d’uscita. Similmente, eseguendo <code>soluzione 21</code> e avendo
 *
 * <pre><code>
 * S Sole 0 0 0
 * P Giove 1 0 0
 * P Marte 0 1 0
 * P Saturno 0 0 1
 * </code></pre>
 *
 * <p>nel flusso di ingresso, il programma emette nel flusso d’uscita
 *
 * <pre><code>
 * Pianeta, nome: Giove, pos: (-2, 0, 0), vel: (-3, 2, 2)
 * Pianeta, nome: Marte, pos: (0, -2, 0), vel: (2, -3, 2)
 * Pianeta, nome: Saturno, pos: (0, 0, -2), vel: (2, 2, -3)
 * Stella fissa, nome: Sole, pos: (0, 0, 0)
 * Energia totale: 42
 * </code></pre>
 */
package it.unimi.di.prog2.h18;
