package fr.eulbobo.dojo.gameoflife.secondary.display;

import fr.eulbobo.dojo.gameoflife.domain.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class StringCellDisplayTest {

    @Test
    void should_display_one_alive_cell() {
        StringCellDisplay stringCellDisplay = new StringCellDisplay('0', '.');

        stringCellDisplay.alive(new Position(0, 0));

        StringBuilder sb = new StringBuilder();
        stringCellDisplay.printTo(sb::append);

        Assertions.assertThat(sb.toString()).isEqualToNormalizingNewlines("""
                0
                """);
    }

    @Test
    void should_display_one_dead_cell() {
        StringCellDisplay stringCellDisplay = new StringCellDisplay('0', '.');

        stringCellDisplay.dead(new Position(0, 0));

        StringBuilder sb = new StringBuilder();
        stringCellDisplay.printTo(sb::append);

        Assertions.assertThat(sb.toString()).isEqualToNormalizingNewlines("""
                .
                """);
    }

    @Test
    void should_display_cells_correctly() {
        StringCellDisplay stringCellDisplay = new StringCellDisplay('0', '.');

        // une cellule sur 2 vivante
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if ((x + y) % 2 == 0) {
                    stringCellDisplay.alive(new Position(x, y));
                } else {
                    stringCellDisplay.dead(new Position(x, y));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        stringCellDisplay.printTo(sb::append);

        Assertions.assertThat(sb.toString()).isEqualToNormalizingNewlines("""
                0.0
                .0.
                0.0
                """);
    }
}