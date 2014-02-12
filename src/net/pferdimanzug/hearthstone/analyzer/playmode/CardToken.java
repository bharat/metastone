package net.pferdimanzug.hearthstone.analyzer.playmode;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import net.pferdimanzug.hearthstone.analyzer.game.Player;
import net.pferdimanzug.hearthstone.analyzer.game.cards.Card;
import net.pferdimanzug.hearthstone.analyzer.game.cards.CardType;
import net.pferdimanzug.hearthstone.analyzer.game.cards.MinionCard;

public class CardToken extends BorderPane {
	
	@FXML
	protected Text manaCostLabel;
	@FXML
	protected Label nameLabel;
	@FXML
	protected Label descriptionLabel;

	@FXML
	protected Text attackLabel;
	@FXML
	protected Text hpLabel;

	@FXML
	protected ImageView attackIcon;
	@FXML
	protected ImageView hpIcon;
	
	protected CardToken(String fxml) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
	
	public void setCard(Card card, Player player) {
		nameLabel.setText(card.getName());
		manaCostLabel.setText(String.valueOf(card.getManaCost(player)));
		boolean isMinionCard = card.getCardType() == CardType.MINION;
		attackLabel.setVisible(isMinionCard);
		hpLabel.setVisible(isMinionCard);
		attackIcon.setVisible(isMinionCard);
		hpIcon.setVisible(isMinionCard);
		if (isMinionCard) {
			MinionCard minionCard = (MinionCard) card;
			attackLabel.setText(String.valueOf(minionCard.getBaseAttack()));
			hpLabel.setText(String.valueOf(minionCard.getBaseHp()));
		}
	}
	
	private void setScoreValue(Text label, int value, int baseValue) {
		label.setText(String.valueOf(value));
		if (value > baseValue) {
			label.setFill(Color.GREEN);
		}
		else if (value < baseValue) {
			label.setFill(Color.RED);
		}
		else {
			label.setFill(Color.WHITE);
		}
	}

}