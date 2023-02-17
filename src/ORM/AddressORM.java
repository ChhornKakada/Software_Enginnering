package ORM;

import java.sql.*;
import java.util.ArrayList;

import Model.Address;

public class AddressORM extends ORM<Address> {
    public AddressORM() {
        tableName = "addresses";
    }

    @Override
    public ArrayList<Address> listAll() {
        ArrayList<Address> arr = new ArrayList<>();
        try (var stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM `" + tableName + "`");
            while (resultSet.next()) {
                arr.add(convertFromResultSetToAddress(resultSet));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return arr;
    }

    @Override
    public Address add(Address data) {
        try (var stmt = connection.prepareStatement("INSERT INTO " + tableName + " VALUES(null,?,?,?,?,?,?,?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, data.getHouseno());
            stmt.setString(2, data.getStreetno());
            stmt.setString(3, data.getStreetname());
            stmt.setString(4, data.getVillagename());
            stmt.setString(5, data.getDistrictname());
            stmt.setString(6, data.getCommunnename());
            stmt.setString(7, data.getProvincename());
            stmt.setString(8, data.getCityname());
            stmt.setString(9, data.getCountryname());
            stmt.setInt(10, data.isIscurrent());

            stmt.execute();

            var resultSet = stmt.getGeneratedKeys();
            if (resultSet.next())
                data.setId(resultSet.getInt(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public Boolean delete(int id) {
        try (var statment = connection.prepareStatement("DELETE FROM " + tableName + " WHERE id=" + id)) {
            statment.execute();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void update(Address data) {
        String query = "UPDATE " + tableName
                + " SET houseno = ?, streetno = ?, streetname = ? , villagename = ?, districtname = ?, communename = ?, provincename = ?, cityname = ?, countryname = ?, iscurrent = ? WHERE id="
                + data.getId();
        try (var stmt = connection.prepareStatement(query)) {
            stmt.setString(1, data.getHouseno());
            stmt.setString(2, data.getStreetno());
            stmt.setString(3, data.getStreetname());
            stmt.setString(4, data.getVillagename());
            stmt.setString(5, data.getDistrictname());
            stmt.setString(6, data.getCommunnename());
            stmt.setString(7, data.getProvincename());
            stmt.setString(8, data.getCityname());
            stmt.setString(9, data.getCountryname());
            stmt.setInt(10, data.isIscurrent());
            stmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Address> rawQuery(String query) {
        ArrayList<Address> arr = new ArrayList<>();
        try (var statement = connection.createStatement()) {
            var resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                arr.add(convertFromResultSetToAddress(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    private Address convertFromResultSetToAddress(ResultSet resultSet) throws SQLException {
        return new Address(resultSet.getInt("id"),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getString(6),
                resultSet.getString(7),
                resultSet.getString(8),
                resultSet.getString(9),
                resultSet.getString(10),
                resultSet.getInt(11)

        );
    }

    public static void main(String[] args) {
        ArrayList<Address> arr = new ArrayList<>();
        AddressORM orm = new AddressORM();

        var newAddress_1 = new Address(0, "12", "32", "123", "123", "123", "comm", "pro", "city", "count", 1);
        orm.add(newAddress_1);

        arr = orm.listAll();
        for (Address data : arr) {
            // System.out.println("\t" +
            // data.getId() + " \t" +
            // data.getHouseno() + "\t" +
            // data.str() + "\t" +
            // data.getRole() + "\t" +
            // data.getGroup() + "\t" +
            // data.getRemoteAddr() + "\t" +
            // data.getForwardAddr() + "\t" +
            // data.getImage()
            // );
            System.out.println(data);
        }

        var newAddress_2 = new Address(0, "123131231232132", "32", "123", "123", "123", "comm", "pro", "city", "count",
                2);
        orm.update(newAddress_2);

        arr = orm.listAll();
        for (Address data : arr) {
            // System.out.println("\t" +
            // data.getId() + " \t" +
            // data.getAddressname() + "\t" +
            // data.getPwd() + "\t" +
            // data.getRole() + "\t" +
            // data.getGroup() + "\t" +
            // data.getRemoteAddr() + "\t" +
            // data.getForwardAddr() + "\t" +
            // data.getImage()
            // ); }
            System.out.println(data);

            arr = orm.rawQuery("SELECT * FROM " + orm.tableName + " WHERE id=1");
            // for (Address data : arr) {
            // System.out.println("\t" +
            // data.getId() + " \t" +
            // data.getAddressname() + "\t" +
            // data.getPwd() + "\t" +
            // data.getRole() + "\t" +
            // data.getGroup() + "\t" +
            // data.getRemoteAddr() + "\t" +
            // data.getForwardAddr() + "\t" +
            // data.getImage()
            // );
            System.out.println(data);
            // }
        }

    }
}
