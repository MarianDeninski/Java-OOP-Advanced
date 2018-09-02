package hell.core.commands;

import hell.core.BaseCommand;

public class HeroCommand extends BaseCommand {
    @Override
    public String execute() {
        return super.getHeroController().createHero(super.getParams().get(0), super.getParams().get(1));
    }
}
