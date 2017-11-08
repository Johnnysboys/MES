package wonton;
import wonton.types.ConstStatements;
import wonton.types.DataTypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.join;

public class SQLConstructor {
    public static String update(Model model, List<Data> data, int id){
        StringBuilder query = new StringBuilder();
        query.append(join(" ", ConstStatements.UPDATE.toString(), model.getTableName()));
        String[] setStatements = new String[data.size()];
        for(int i = 0; i < data.size(); i++){
            Data d = data.get(i);
            setStatements[i] = join(" ", d.getField(), "=", "?");
        }
        query.append(" SET " + join(", ", setStatements));
        query.append(" WHERE id = " + id + ";");
        return query.toString();
    }

    public static String insert(Model model, List<Data> data) throws Exception {
        return insert(model, data, true);
    }

    public static String insert(Model model, List<Data> data, Boolean returnId) throws Exception {
        StringBuilder query = new StringBuilder();
        int numOfData = data.size();
        String[] fields = new String[numOfData];
        String[] placeholders = new String[numOfData];
        for(int i = 0; i < numOfData; i++){
            fields[i] = data.get(i).getField();
            placeholders[i] = "?";
        }
        String fieldsString = join(", ", fields);
        String dataString = join(", ", placeholders);
        query.append(join(" ",ConstStatements.INSERT.toString(), "INTO", model.getTableName()));
        query.append(join(" ", "(", fieldsString, ") VALUES (", dataString, ")"));

        if(returnId){
            query.append("RETURNING id");
        }
        query.append(";");

        return query.toString();
    }

    public static String createTable(Model model) {
        StringBuilder query = new StringBuilder();
        query.append(join(" ",
                ConstStatements.CREATE.toString(),
                ConstStatements.TABLE.toString(),
                ConstStatements.IFNOT.toString(),
                constructTableFromModel(model)));
        return query.toString();
    }

    private static String constructTableFromModel(Model model){
        StringBuilder sql = new StringBuilder();
        String[] fields = constructFields(model.getDefinitions(), model);
        sql.append(model.getTableName() + " (" + join(",",fields) + ")");
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

    private static String selectQuery(Model model, String[] attributes, List<Parameter> parameters){
        StringBuilder query = new StringBuilder();
        query.append(join(" ",
                ConstStatements.SELECT.toString(),
                join(",", attributes),
                "FROM",
                model.getTableName()));
        if(parameters != null && parameters.size() > 0){
            String[] parameterString = new String[parameters.size()];
            for(int i = 0; i < parameters.size(); i++){
                parameterString[i] = parameters.get(i).toString();
            }

            query.append(" WHERE " + join(" AND ", parameterString));
        }
        query.append(";");
        return query.toString();
    }
    public static String select(Model model, List<Parameter> parameters){
        return selectQuery(model, new String[]{"*"}, parameters);
    }
    public static String select(Model model, Parameter... parameters){
        return selectQuery(model, new String[]{"*"}, Arrays.asList(parameters));
    }
    public static String select(Model model, String... attributes){
        return selectQuery(model, attributes, null);
    }


    public static String delete(Model model, int id){
        StringBuilder query = new StringBuilder();
        query.append(join(" ", ConstStatements.DELETE.toString(), "FROM", model.getTableName(), "WHERE id = " + id + ";"));
        return query.toString();
    }


}
