package fr.eulbobo.dojo.gameoflife.domain.generator;

import java.util.Collection;

public interface CellGenerator {
    Collection<CellData> allCells();
}
