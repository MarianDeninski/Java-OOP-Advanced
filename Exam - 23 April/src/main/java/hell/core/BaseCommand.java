package hell.core;

import hell.annotations.Inject;
import hell.interfaces.Executable;
import hell.interfaces.HeroController;

import java.util.Collections;
import java.util.List;

public abstract class BaseCommand implements Executable {

    @Inject
    private List<String> params;
    @Inject
    private HeroController heroController;

    protected BaseCommand() {
    }

    protected List<String> getParams() {
        return Collections.unmodifiableList(this.params);
    }

    protected HeroController getHeroController() {
        return this.heroController;
    }
}