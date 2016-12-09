package ui.scenes.editor.sidemenu;

/**
 * @author Filip Mazurek, Aninda Manocha
 */
public interface GameEditorAlerts {
    void exceptionDisplay(String content);
    boolean warnUser(String warningKey);
}
