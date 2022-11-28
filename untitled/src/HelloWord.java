public class HelloWord {
    public static void main (String args[])
    {
        System.out.println ("hello world");
        vuner;
    }

    public void vuner(){
        String firstname = "firstname";
        String lastname = lastname";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection( "url", "aa", "password);
        String query = "SELECT id, firstname, lastname FROM authors WHERE firstname = ? and lastname = ?";
        PreparedStatement pstmt = connection.prepareStatement( query );
        pstmt.setString( 1, firstname );
        pstmt.setString( 2, lastname );
        try
        {
            ResultSet results = pstmt.execute( );
        }
    }
}
