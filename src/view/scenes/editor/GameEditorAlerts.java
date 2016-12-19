package view.scenes.editor;

/**
 * @author Filip Mazurek, Aninda Manocha
 */
public interface GameEditorAlerts {

    void exceptionDisplay(String content);

    boolean warnUser(String warningKey);
}
