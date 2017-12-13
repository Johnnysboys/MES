package wonton;
import wonton.types.Statements;
import wonton.types.DataTypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.join;

public class SQLConstructor {
    public static String insert(Model model, List<Data> data) throws Exception {
        return insert(model, data, true);
    }

    public static  synchronized String insert(Model model, List<Data> data, Boolean returnId) throws Exception {
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
        query.append(join(" ", Statements.INSERT.toString(), "INTO", model.getTableName()));
        query.append(join(" ", "(", fieldsString, ") VALUES (", dataString, ")"));

        if(returnId){
            query.append("RETURNING id");
        }
        query.append(";");

        return query.toString();
    }

    public static  synchronized String createTable(Model model) {
        StringBuilder query = new StringBuilder();
        query.append(join(" ",
                Statements.CREATE.toString(),
                Statements.TABLE.toString(),
                Statements.IFNOT.toString(),
                constructTableFromModel(model)));
        return query.toString();
    }

    private static  synchronized String constructTableFromModel(Model model){
        StringBuilder sql = new StringBuilder();
        String[] fields = constructFields(model.getDefinitions(), model);
        sql.append(model.getTableName() + " (" + join(",",fields) + ")");
        return sql.toString();
    }

    private static  synchronized String[] constructFields(ArrayList<Definition> definitions, Model model){
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
                fields[index] += " " + Statements.DEFAULT;
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
    private static  synchronized String selectQuery(Model model, String[] attributes, List<Parameter> parameters){
        StringBuilder query = new StringBuilder();
        query.append(join(" ",
                Statements.SELECT.toString(),
                join(",", attributes),
                "FROM",
                model.getTableName()));
        if(parameters != null)
            query.append(" WHERE " + join(" AND ", parameterBuilder(parameters)));
        query.append(";");
        return query.toString();
    }

    public static  synchronized String select(Model model){
        return select(model, "*");
    }

    public static  synchronized String select(Model model, List<Parameter> parameters){
        return selectQuery(model, new String[]{"*"}, parameters);
    }

    public static  synchronized String select(Model model, Parameter... parameters){
        return selectQuery(model, new String[]{"*"}, Arrays.asList(parameters));
    }
    public static  synchronized String select(Model model, String... attributes){
        return selectQuery(model, attributes, null);
    }

    public static  synchronized String delete(Model model, int id){
        StringBuilder query = new StringBuilder();
        query.append(join(" ", Statements.DELETE.toString(), "FROM", model.getTableName(), "WHERE id = " + id + ";"));
        return query.toString();
    }

    public static  synchronized String update(Model model, List<Data> data, int id){
        StringBuilder query = new StringBuilder();
        query.append(join(" ", Statements.UPDATE.toString(), model.getTableName()));
        String[] setStatements = new String[data.size()];
        for(int i = 0; i < data.size(); i++){
            Data d = data.get(i);
            setStatements[i] = join(" ", d.getField(), "=", "?");
        }
        query.append(" SET " + join(", ", setStatements));
        query.append(" WHERE id = " + id + ";");
        return query.toString();
    }
    public static synchronized String update(Model model, List<Data> data, Parameter... parameters){
        StringBuilder query = new StringBuilder();
        query.append(join(" ", Statements.UPDATE.toString(), model.getTableName()));
        String[] setStatements = new String[data.size()];
        for(int i = 0; i < data.size(); i++){
            //System.out.println(data.get(i));
            Data d = data.get(i);
            setStatements[i] = join(" ", d.getField(), "=", "?");
        }
        query.append(" SET " + join(", ", setStatements));
        query.append(" WHERE " + join(" AND ", parameterBuilder(Arrays.asList(parameters))));
        return query.toString();
    }

    private static synchronized  String[] parameterBuilder(List<Parameter> parameters){
        if(parameters != null && parameters.size() > 0){
            String[] parameterString = new String[parameters.size()];
            for(int i = 0; i < parameters.size(); i++) {
                parameterString[i] = parameters.get(i).toString();
            }
            return parameterString;
        }
        return null;
    }



}
