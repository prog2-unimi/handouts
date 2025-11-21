/**
 * Soluzione relativa all'esercizio della lezione 17 per maggiori dettagli si veda il <a
 * href="https://prog2.di.unimi.it/diario">diario del corso</a>.
 *
 * <p><em>Questo esercizio è stato assegnato come <strong>tema d’esame</strong> nell’appello del 21
 * gennaio 2020 ed è ispirato a <strong><a href="https://adventofcode.com/2019/day/12">The N-Body
 * Problem</a></strong>, il dodicesimo problema dell'<a href="https://adventofcode.com/">Advent of
 * Code</a> del 2019.</em>
 *
 * <h2>Astronomical System</h2>
 *
 * <p>The purpose of the exercise is to design and implement a hierarchy of objects useful to
 * represent and simulate the behavior of an <em>astronomical system</em> composed of some
 * <em>celestial bodies</em> (such as <em>planets</em> and <em>stars</em>) subject to mutual
 * gravitational interaction.
 *
 * <p>You will need to decide which classes (concrete or abstract) to implement. For each of them,
 * you will need to describe in Javadoc format the choices related to the representation of the
 * state (with particular reference to the <em>representation invariant</em> and the <em>abstraction
 * function</em>) and the methods (with particular reference to
 * <em>pre-</em>/<em>post-conditions</em> and <em>side effects</em>).
 *
 * <p>A <strong>celestial body</strong> is characterized by a <em>name</em> and a <em>position</em>,
 * described by a <strong>three-dimensional point</strong> with integer coordinates; the
 * <em>norm</em> of a three-dimensional point is the sum of the absolute values of its components
 * (also known as the <span class="math inline">ℓ<sub>1</sub></span> norm). We will assume for
 * simplicity that there are only two types of celestial bodies: <strong>stars</strong> and
 * <strong>planets</strong>. Stars never change their position, unlike planets. Therefore, in
 * addition to position, planets are characterized by their <em>velocity</em>, also described by a
 * three-dimensional point. Each celestial body has an <em>energy</em> given by the product of the
 * <em>potential energy</em>, corresponding to the norm of its position, and the <em>kinetic
 * energy</em>, corresponding to the norm of its velocity (obviously the kinetic energy is zero in
 * the case of fixed stars).
 *
 * <p>Celestial bodies are subject to mutual <em>gravitational attraction</em> which modifies their
 * velocity and, indirectly, their position as follows:
 *
 * <ul>
 *   <li>
 *       <p>first, each planet <em>modifies its velocity</em> based on the attraction towards all
 *       other celestial bodies: given the planet {@code p} and the celestial body {@code c},
 *       independently for each of the three coordinates, the velocity of {@code p} changes by +1 or
 *       -1 depending on whether that coordinate is, respectively, less than or greater than that of
 *       {@code c};
 *   <li>
 *       <p>once the new velocity for all planets has been calculated, each planet <em>modifies its
 *       position</em> by adding the value of its velocity to that of its position (as if the planet
 *       were subject to uniform motion for one unit of time).
 * </ul>
 *
 * <p>For example, if the system included only two planets and initially the {@code x} coordinate of
 * Mars position was 3 and that of Jupiter was 5, then the {@code x} coordinate of Mars' velocity
 * would change by +1 (because 3 &lt; 5) while that of Jupiter would change by -1 (because 5 &gt;
 * 3). Since initially the velocities are zero, after this update the {@code x} coordinate of Mars
 * velocity would be 1, while that of Jupiter would be -1 and updating the position of the two
 * planets would bring the {@code x} coordinate of both positions to 4. Consequently, in the next
 * update their two velocities (but not their positions) would remain unchanged.
 *
 * <p>An <strong>astronomical system</strong> is a collection of planets and fixed stars. It is
 * characterized by a <em>state</em> that evolves in <em>discrete time</em>: at time 0 the state is
 * given by the list of all celestial bodies that compose it, with the assigned position and zero
 * velocity, then for each time step the two updates described above occur:
 *
 * <ul>
 *   <li>each planet (interacting with all celestial bodies in the system) updates its velocity,
 *   <li>after the new velocities have been calculated for all planets, each planet proceeds to
 *       update its position.
 * </ul>
 *
 * <p>This determines the new state, consisting of the list of all celestial bodies with
 * appropriately updated positions and velocities. The <em>total energy</em> of an astronomical
 * system in a certain state is given by the sum of the energy of all the celestial bodies that
 * compose it.
 *
 * <h2 id="test-class">Test Class</h2>
 *
 * <p>Complete the {@link it.unimi.di.prog2.e17.AstronomicalSystemClient} main method so that it
 * reads from the input stream a sequence of quintuples corresponding to the various celestial
 * bodies; each quintuple is given by:
 *
 * <ul>
 *   <li>a character that can be {@code S} or {@code P} to indicate, respectively, that the
 *       celestial body is a star or a planet,
 *   <li>a string (which does not contain spaces) indicating the name of the celestial body,
 *   <li>three integers indicating the initial coordinates of the celestial body.
 * </ul>
 *
 * <p>These quintuples should be used to populate an astronomical system that must be evolved a
 * number of steps equal to the integer indicated as a parameter on the command line. At the end of
 * the evolution, the list of celestial bodies in the system (in alphabetical order by name and with
 * data related to position and velocity) should be printed, and finally the total energy of the
 * system should be printed.
 *
 * <h2 id="input">Input</h2>
 *
 * <p>You can assume that the input has the specified format, that all numbers involved in the
 * execution of the code are integers (and can be represented by variables of type {@code int} for
 * position and velocity, and of type {@code long} for energies). Therefore, a plausible way to read
 * the information provided in input is as follows:
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
 * <h2 id="example">Examples</h2>
 *
 * <p>Running {@code solution 1} and having
 *
 * <pre><code>
 * P Mars -8 -10 0
 * P Jupiter 5 5 10
 * P Saturn 2 -7 3
 * P Venus 9 -8 -3
 * </code></pre>
 *
 * <p>in the input stream, the program outputs
 *
 * <pre><code>
 * Planet, name: Jupiter, pos: (4, 2, 7), vel: (-1, -3, -3)
 * Planet, name: Mars, pos: (-5, -7, 1), vel: (3, 3, 1)
 * Planet, name: Saturn, pos: (3, -8, 2), vel: (1, -1, -1)
 * Planet, name: Venus, pos: (6, -7, 0), vel: (-3, 1, 3)
 * Total Energy: 312
 * </code></pre>
 *
 * <p>Similarly, with the same input, running {@code solution 100} outputs
 *
 * <pre><code>
 * Planet, name: Jupiter, pos: (13, 16, -3), vel: (3, -11, -5)
 * Planet, name: Mars, pos: (8, -12, -9), vel: (-7, 3, 0)
 * Planet, name: Saturn, pos: (-29, -11, -1), vel: (-3, 7, 4)
 * Planet, name: Venus, pos: (16, -13, 23), vel: (7, 1, 1)
 * Total Energy: 1940
 * </code></pre>
 *
 * <p>in the output stream. Similarly, running {@code solution 21} and having
 *
 * <pre><code>
 * S Sun 0 0 0
 * P Jupiter 1 0 0
 * P Mars 0 1 0
 * P Saturn 0 0 1
 * </code></pre>
 *
 * <p>in the input stream, the program outputs
 *
 * <pre><code>
 * Planet, name: Jupiter, pos: (-2, 0, 0), vel: (-3, 2, 2)
 * Planet, name: Mars, pos: (0, -2, 0), vel: (2, -3, 2)
 * Planet, name: Saturn, pos: (0, 0, -2), vel: (2, 2, -3)
 * Star, name: Sun, pos: (0, 0, 0)
 * Total Energy: 42
 * </code></pre>
 */
package it.unimi.di.prog2.s17;
