package ORM;
import java.util.ArrayList;
import java.util.List;

public class SQLConstructor {
    public static String insert(List<Data> data, Model model) throws Exception {
        return insert(data, model, true);
    }

    public static String insert(List<Data> data, Model model, Boolean returnId) throws Exception {
        StringBuilder query = new StringBuilder();
        int numOfData = data.size();
        String[] fields = new String[numOfData];
        String[] placeholders = new String[numOfData];
        for(int i = 0; i < numOfData; i++){
            fields[i] = data.get(i).getField();
            placeholders[i] = "?";
        }
        String fieldsString = String.join(", ", fields);
        String dataString = String.join(", ", placeholders);
        query.append(String.join(" ",ConstStatements.INSERT.toString(), "INTO", model.getTableName()));
        query.append(String.join(" ", "(", fieldsString, ") VALUES (", dataString, ")"));

        if(returnId){
            query.append("RETURNING id");
        }
        query.append(";");

        return query.toString();
    }

    public static String createTable(Model model) {
        StringBuilder query = new StringBuilder();
        query.append(String.join(" ",
                ConstStatements.CREATE.toString(),
                ConstStatements.TABLE.toString(),
                ConstStatements.IFNOT.toString(),
                constructTableFromModel(model)));
        return query.toString();
    }

    private static String constructTableFromModel(Model model){
        StringBuilder sql = new StringBuilder();
        String[] fields = constructFields(model.getDefinitions(), model);
        sql.append(model.getTableName() + " (" + String.join(",",fields) + ")");
        return sql.toString();
    }
    private static String[] constructFields(ArrayList<Definition> definitions, Model model){
        String[] fields = new String[model.countFields()];
        int index = 0;
        for(Definition definition : definitions){
            fields[index] = "";
            fields[index] += definition.getFieldName() + " " + definition.getType();
            ArrayList<Constraint> constraints = definition.getConstraints();
            if(constraints != null){
                for(Constraint constraint : constraints){
                    fields[index] += " " + constraint.toString();
                }
            }
            if(definition.getDefaultValue() != null){
                fields[index] += " " + ConstStatements.DEFAULT;
                if(definition.getType() == DataTypes.STRING
                        || definition.getType() == DataTypes.TEXT
                        || definition.getType() == DataTypes.DATE
                        || definition.getType() == DataTypes.ENUM ) {
                    fields[index] += " '" + definition.getDefaultValue() + "'";
                } else {
                    fields[index] += definition.getDefaultValue();
                }
            }
            index++;
        }
        return fields;
    }

    public static String select(Model model){
        return select(model, "*");
    }

    public static String select(Model model, String... attributes){
        StringBuilder query = new StringBuilder();
        query.append(String.join(" ",
                ConstStatements.SELECT.toString(),
                String.join(",", attributes),
                "FROM",
                model.getTableName(),
                ";"));
                return query.toString();
    }
    public static String



}
