package kp.resource.server.model;

/**
 * Represents four classification levels, ordered from the weakest to the strongest classification.
 * Each level corresponds to a typical classification hierarchy, with increasing levels of confidentiality.
 * Each classification level is associated with a specific color-coded background style for display purposes.
 */
public enum ClassificationLevel {
    /**
     * <b>Official</b> - The lowest classification level,
     * suitable for general information that does not require significant protection.
     */
    OFFICIAL("green"),
    /**
     * <b>Restricted</b> - A mid-tier classification level,
     * denoting information that requires limited access controls.
     */
    RESTRICTED("blue"),
    /**
     * <b>Confidential</b> - A higher classification level,
     * indicating sensitive information requiring strict access restrictions.
     */
    CONFIDENTIAL("yellow"),
    /**
     * <b>Secret</b> - The highest classification level,
     * reserved for highly sensitive information requiring the most stringent safeguards.
     */
    SECRET("red");

    private final String textareaClass;
    private static final String HTML_PAGE_FMT = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <title>Resource Server</title>
                <style>
                    body {
                      background-color: wheat
                    }
                    th, td {
                      border: 2px solid black;
                      font-size: 1.5em;
                      padding: 5px;
                    }
                    textarea {
                      padding: 10px 10px;
                      border-radius: 4px;
                      font-size: 16px;
                      resize: none;
                    }
                    textarea.green {
                      background-color: lightgreen;
                      border: 2px solid green;
                    }
                    textarea.blue {
                      background-color: lightblue;
                      border: 2px solid blue;
                    }
                    textarea.yellow {
                      background-color: yellow;
                      border: 2px solid orange;
                    }
                    textarea.red {
                      background-color: lightpink;
                      border: 2px solid red;
                    }
                </style>
            </head>
            <body>
            <h1>Resource Server</h1>
            <h2>Document Endpoints</h2>
            <table>
                <tr><td><a href="http://localhost:9092/api/document/official">
                        http://localhost:9092/api/document/official</a></td></tr>
                <tr><td><a href="http://localhost:9092/api/document/restricted">
                        http://localhost:9092/api/document/restricted</a></td></tr>
                <tr><td><a href="http://localhost:9092/api/document/confidential">
                        http://localhost:9092/api/document/confidential</a></td></tr>
                <tr><td><a href="http://localhost:9092/api/document/secret">
                        http://localhost:9092/api/document/secret</a></td></tr>
            </table>
            
            <h2>Document with Classification Level "%s"</h2>
            <textarea class="%s" cols="30" rows="2">%s</textarea>
            <hr>
            <table>
                <tr>
                    <td>Resource Server Information</td>
                    <td><a href="http://localhost:9092/api/info">
                        http://localhost:9092/api/info</a>
                    </td>
                </tr>
            </table>
            <hr>
            </body>
            </html>
            """;

    /**
     * Parameterized constructor.
     * Constructs a classification level with a corresponding text area class for display styling.
     *
     * @param textareaClass the CSS class defining the background color for the classification level
     */
    ClassificationLevel(String textareaClass) {
        this.textareaClass = textareaClass;
    }

    /**
     * Generates an HTML page representing a document with the current classification level.
     *
     * @param document the content of the document to be included in the HTML page
     * @return the generated HTML page as a string
     */
    public String createHtmlPageWithDocument(String document) {
        return HTML_PAGE_FMT.formatted(this.name(), this.textareaClass, document);
    }
}