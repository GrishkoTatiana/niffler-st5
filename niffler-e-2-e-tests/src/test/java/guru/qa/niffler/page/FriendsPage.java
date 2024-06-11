package guru.qa.niffler.page;

/**
 * Страница FRIENDS
 *
 * <br><img src="FriendsPage.png" width="825" height="239"/>
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
