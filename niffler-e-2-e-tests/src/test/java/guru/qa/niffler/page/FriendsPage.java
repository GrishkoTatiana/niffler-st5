package guru.qa.niffler.page;

/**
 * Страница FRIENDS
 */
public class FriendsPage {
    /**
     * Панель кнопок
     */
    private ButtonPanelPage buttonPanelPage;
    public FriendsPage() {
        buttonPanelPage = new ButtonPanelPage();
    }

    public ButtonPanelPage getButtonPanelPage() {
        return buttonPanelPage;
    }

}
