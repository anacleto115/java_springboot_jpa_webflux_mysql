
package com.example.ut_presentation;

public class Ut_presentation 
{
    public static void main(String[] args) 
    {
        UnitTests();
    }

    private static void UnitTests()
    {
        try
        {
            ConnectionsUnitTest();
            RepositoriesUnitTest();
        }
        catch (Exception ex)
        {
            System.out.println(ex.toString());
        }
    }

    private static void ConnectionsUnitTest() throws Exception
    {
        (new ut_presentation.Connections.AuditsTest()).Execute();
        (new ut_presentation.Connections.ProductsTest()).Execute();
    }

    private static void RepositoriesUnitTest() throws Exception
    {
        (new ut_presentation.Repositories.AuditsTest()).Execute();
        (new ut_presentation.Repositories.ProductsTest()).Execute();
    }
}
