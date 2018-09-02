package hell.core.commands;

import hell.core.BaseCommand;

import java.util.stream.Collectors;

public class RecipeCommand extends BaseCommand {
    @Override
    public String execute() {
        return super.getHeroController().createRecipe(
                super.getParams().get(0),
                super.getParams().get(1),
                Integer.parseInt(super.getParams().get(2)),
                Integer.parseInt(super.getParams().get(3)),
                Integer.parseInt(super.getParams().get(4)),
                Integer.parseInt(super.getParams().get(5)),
                Integer.parseInt(super.getParams().get(6)),
                super.getParams().stream().skip(7).collect(Collectors.toList()));
    }
}
