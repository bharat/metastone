package net.demilich.metastone.game.spells;

import java.util.List;

import net.demilich.metastone.game.Attribute;
import net.demilich.metastone.game.GameContext;
import net.demilich.metastone.game.Player;
import net.demilich.metastone.game.cards.Card;
import net.demilich.metastone.game.cards.CardType;
import net.demilich.metastone.game.entities.Actor;
import net.demilich.metastone.game.entities.Entity;
import net.demilich.metastone.game.entities.EntityType;
import net.demilich.metastone.game.spells.desc.SpellArg;
import net.demilich.metastone.game.spells.desc.SpellDesc;

public class MissilesSpell extends DamageSpell {

	@Override
	public void cast(GameContext context, Player player, SpellDesc desc, Entity source, List<Entity> targets) {
		int missiles = desc.getInt(SpellArg.HOW_MANY, 2);
		int damage = desc.getInt(SpellArg.VALUE, 1);

		if (source.getEntityType() == EntityType.CARD && ((Card) source).getCardType() == CardType.SPELL) {
			missiles = context.getLogic().applySpellpower(player, source,  missiles);
			missiles = context.getLogic().applyAmplify(player, missiles, Attribute.SPELL_AMPLIFY_MULTIPLIER);
		}
		for (int i = 0; i < missiles; i++) {
			List<Actor> validTargets = SpellUtils.getValidRandomTargets(targets);
			Actor randomTarget = SpellUtils.getRandomTarget(validTargets);
			context.getLogic().damage(player, randomTarget, damage, source, true);
		}
	}

	@Override
	protected void onCast(GameContext context, Player player, SpellDesc desc, Entity source, Entity target) {
	}

}
