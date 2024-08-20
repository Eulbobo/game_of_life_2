package fr.eulbobo.dojo.gameoflife.primary.generator;

import fr.eulbobo.dojo.gameoflife.domain.Position;
import fr.eulbobo.dojo.gameoflife.domain.generator.CellData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.assertj.core.groups.Tuple.tuple;

class StringCellGeneratorTest {

    @Test
    void should_generate_cell_data_from_string_board() {
        StringCellGenerator cellGenerator = new StringCellGenerator("""
                ..0
                .0.
                0..
                """, '0');

        Collection<CellData> cells = cellGenerator.allCells();
        Assertions.assertThat(cells).hasSize(9)
                .extracting("position", "alive").containsExactlyInAnyOrder(
                        tuple(new Position(2, 0), true),
                        tuple(new Position(1, 1), true),
                        tuple(new Position(0, 2), true),
                        tuple(new Position(0, 0), false),
                        tuple(new Position(1, 0), false),
                        tuple(new Position(0, 1), false),
                        tuple(new Position(2, 1), false),
                        tuple(new Position(1, 2), false),
                        tuple(new Position(2, 2), false)
                );
    }

}