package com.karan.impactcli;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(
    name = "impact",
    mixinStandardHelpOptions = true,
    version = "impact-cli 1.1.0",
    description = "Personalized CLI for impact.com (SaaSquatch). Prints a banner and a short pitch."
)
public class ImpactCli implements Callable<Integer> {

    @Option(names = {"-n","--name"}, defaultValue = "Karanveer Dubey",
            description = "Your name to personalize output.")
    String name;

    @Option(names = {"-r","--role"}, defaultValue = "Java Software Engineer Co-op",
            description = "Role you are applying for.")
    String role;

    @Option(names = {"-w","--why"}, defaultValue = "I learn fast, problem solving genuinely excites me and love logical challenges.",
            description = "One-liner on why you want to work here.")
    String why;

    @Option(names = {"-b","--banner"}, defaultValue = "impact.com",
            description = "Text to render as ASCII banner.")
    String bannerText;

    @Override
    public Integer call() {
        System.out.println(render(bannerText));
        System.out.printf("Hello, impact.com! I'm %s applying for %s.%n", name, role);
        System.out.println("Why me: " + why);
        System.out.println("\nStack highlights: Java (ramping), TypeScript/React, SQL, REST, Docker, CI/CD.");
        return 0;
    }

    public static void main(String[] args) {
        int exit = new CommandLine(new ImpactCli()).execute(args);
        System.exit(exit);
    }

    // ======= ASCII banner (minimal, embedded) =======
    private static String render(String text) {
        String t = (text == null || text.isBlank()) ? "impact.com" : text;
        String[] lines = new String[6];
        for (int i = 0; i < 6; i++) lines[i] = "";
        for (char ch : t.toCharArray()) {
            String[] g = glyph(ch);
            for (int r = 0; r < 6; r++) lines[r] += g[r] + "  ";
        }
        return String.join(System.lineSeparator(), lines) + System.lineSeparator();
    }
    private static String[] glyph(char c) {
        switch (Character.toLowerCase(c)) {
            case 'i': return new String[]{" #### ","  ##  ","  ##  ","  ##  ","  ##  "," #### "};
            case 'm': return new String[]{"##  ##","######","######","# ## #","##  ##","##  ##"};
            case 'p': return new String[]{"##### ","##  ##","##### ","##    ","##    ","##    "};
            case 'a': return new String[]{" #### ","##  ##","######","##  ##","##  ##","##  ##"};
            case 'c': return new String[]{" #### ","##  ##","##    ","##    ","##  ##"," #### "};
            case 't': return new String[]{"######","  ##  ","  ##  ","  ##  ","  ##  ","  ##  "};
            case '.': return new String[]{"  ","  ","  ","  ","##","##"};
            case 'o': return new String[]{" #### ","##  ##","##  ##","##  ##","##  ##"," #### "};
            default:  return new String[]{"  ","  ","  ","  ","  ","  "};
        }
    }
}
