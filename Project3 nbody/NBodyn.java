public class NBodyn {
    public static void main(String[] args) {
        double tau = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        double G = 0.0000000000667;


        // Step 2. Read universe from standart input
        int n = StdIn.readInt();
        double radius = StdIn.readDouble();
        double[] px = new double[n];
        double[] py = new double[n];
        double[] vx = new double[n];
        double[] vy = new double[n];
        double[] mass = new double[n];
        String[] image = new String[n];

        for (int i = 0; i < n; i++) {
            px[i] = StdIn.readDouble();
            py[i] = StdIn.readDouble();
            vx[i] = StdIn.readDouble();
            vy[i] = StdIn.readDouble();
            mass[i] = StdIn.readDouble();
            image[i] = StdIn.readString();
        }
        // Step 3. Initialize standart drawing.
        StdDraw.setXscale(-radius, +radius);
        StdDraw.setYscale(-radius, +radius);
        StdDraw.enableDoubleBuffering();
        // Step 4. Play music on standart audio.
        // StdAudio.play("rick.wav");
        StdAudio.play("2001.wav");
        // Step 5. Simulate the universe
        for (double t = 0.0; t < tau; t = t + dt) {

            //StdOut.println(t);
            double fx[] = new double[n];
            double fy[] = new double[n];

            // Step 5A. Calculate net forces.
            for (int i = 0; i < n; i++) {

                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        double dx = px[j] - px[i];
                        double dy = py[j] - py[i];
                        double r = Math.sqrt(dx * dx + dy * dy);
                        double F = G * mass[i] * mass[j] / (r * r);
                        fx[i] += (F * dx) / r;
                        fy[i] += (F * dy) / r;


                    }
                }
            }
            // Step 5B. Update velocities and positions.

            for (int i = 0; i < n; i++) {
                double ax = fx[i] / mass[i];
                double ay = fy[i] / mass[i];
                vx[i] = vx[i] + (ax * dt);
                vy[i] = vy[i] + (ay * dt);
                px[i] = px[i] + vx[i] * dt;
                py[i] = py[i] + vy[i] * dt;

            }

            // Step 5C. Draw universe to standart drawing.
            StdDraw.picture(0, 0, "starfield.jpg");
            for (int j = 0; j < n; j++) {
                StdDraw.picture(px[j], py[j], image[j]);

            }
            StdDraw.show();
            StdDraw.pause(20);

        }

        // Step 6. Print universe to standart output.
        StdOut.printf("%d\n", n);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < n; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          px[i], py[i], vx[i], vy[i], mass[i], image[i]);
        }
    }
}
