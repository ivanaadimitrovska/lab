package mk.ukim.finki.wp.lab.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class TeacherFullnameConverter implements AttributeConverter<TeacherFullname, String> {
    @Override
    public String convertToDatabaseColumn(TeacherFullname attribute) {
        if (attribute == null)
            return null;
        StringBuilder sb = new StringBuilder();
        if (attribute.getName() != null && !attribute.getName().isEmpty()) {
            sb.append(attribute.getName());
            sb.append(" ");
        }
        if (attribute.getSurname() != null && !attribute.getSurname().isEmpty())
        {
            sb.append(attribute.getSurname());
        }
        return sb.toString();
    }

    @Override
    public TeacherFullname convertToEntityAttribute(String dbData) {
        if(dbData == null || dbData.isEmpty())
            return null;
        String[] split = dbData.split(" ");
        if(split.length<=1)
            return null;
        return new TeacherFullname(split[0],split[1]);
    }
}
