public class Paragraph {
    public static void main(String[] args) {
        System.out.println("""
            \\n                           Line break.
            \\t                           Tabulation.
            \\'                         Single Quote.
            \\"                         Double Quote.
            \\\\                               \\ Sign.
            \\\\\\\\                            \\\\ Sign.
            //                         Line Comment.
            /* ... */                 Block Comment.
            \"\"\"
                                         Text block.
            \"\"\"
                """);
    }
}
