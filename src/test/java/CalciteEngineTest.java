import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class CalciteEngineTest {
    @Test
    void testSqlExecution() {
        CalciteEngine engine = new CalciteEngine();
        assertDoesNotThrow(() -> engine.runQuery());
    }
}
