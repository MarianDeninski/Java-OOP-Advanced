package hell.core.commands;

import hell.core.BaseCommand;

public class ItemCommand extends BaseCommand {
    @Override
    public String execute() {
        return super.getHeroController().createItem(
                super.getParams().get(0),
                super.getParams().get(1),
                Integer.parseInt(super.getParams().get(2)),
                Integer.parseInt(super.getParams().get(3)),
                Integer.parseInt(super.getParams().get(4)),
                Integer.parseInt(super.getParams().get(5)),
                Integer.parseInt(super.getParams().get(6)));
    }
}
