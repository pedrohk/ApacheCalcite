import Schema.HrSchema;

public class CalciteEngine {
    public void runQuery() throws Exception {

            HrSchema engine = new HrSchema();
            String path = "src/main/resources/model.json";
            engine.executeJoinQuery(path).forEach(System.out::println);
    }
}