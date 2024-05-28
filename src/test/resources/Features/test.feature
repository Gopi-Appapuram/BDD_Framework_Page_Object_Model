Feature: Risk by Organisation Page

    Background:
        Given Navigate to "Risk by Organisation" page

    Scenario: Verify that the "Potential Risk by Organization" page loads successfully without errors
        Then the page loads successfully without errors

    Scenario: Check that all elements are displayed correctly
        Then all elements are displayed correctly

    Scenario: Test all navigation links and buttons
        When I click on the sidebar icons
        Then I should be redirected to the correct pages
        When I click on the profile dropdown
        Then I should see the profile options

    Scenario: Click on the filters buttons and verify the pop-up
        When I click on the filter buttons
        Then I should see a pop-up with "Organisations", "FEIN", and "CG" DropDowns
        And the dropdowns should be clickable

    Scenario: Select different organizations from the dropdown and verify updates
        When I select different organizations from the dropdown
        Then the results table updates accordingly

    Scenario: Select single organization value from the dropdown and verify updates
        When I select a single organization value from the dropdown
        Then the results table updates accordingly

    Scenario: Ensure organization list in the filter dropdown matches results table
        Then the organization list in the filter dropdown matches the organizations displayed in the results table

    Scenario: Select different FEIN from the dropdown and verify updates
        When I select different FEIN from the dropdown
        Then the results table updates accordingly

    Scenario: Select single FEIN value from the dropdown and verify updates
        When I select a single FEIN value from the dropdown
        Then the results table updates accordingly

    Scenario: Ensure FEIN list in the filter dropdown matches results table
        Then the FEIN list in the filter dropdown matches the FEIN displayed in the results table

    Scenario: Select different CG from the dropdown and verify updates
        When I select different CG from the dropdown
        Then the results table updates accordingly

    Scenario: Select single CG value from the dropdown and verify updates
        When I select a single CG value from the dropdown
        Then the results table updates accordingly

    Scenario: Ensure CG list in the filter dropdown matches results table
        Then the CG list in the filter dropdown matches the CG displayed in the results table

    Scenario: Apply multiple filters simultaneously and verify updates
        When I apply multiple filters (one each from Organization, FEIN, and CG)
        Then the results table displays correctly filtered results
        When I apply multiple filters (more than one from each Organization, FEIN, and CG)
        Then the results table displays correctly filtered results

    Scenario: Test the "Clear Filters" button
        When I apply multiple filters (one each from Organization, FEIN, and CG)
        And I click the "Clear Filters" button
        Then all filters are reset
        And the results table shows all organizations

    Scenario: Test the X button to remove applied filter
        When I apply multiple filters (more than one from each Organization, FEIN, and CG)
        Then the results table displays correctly filtered results
        When I click the X button to remove an applied filter
        Then the results table shows data as per the remaining filters

    Scenario: Test the search bar with valid and invalid keywords
        When I enter different keywords in the search bar
        Then the results update dynamically
        When I enter invalid keywords in the search bar
        Then the results table displays "No data found"

    Scenario: Test entering data in the search box
        When I enter data into the search box
        Then the data is accepted

    Scenario: Test searching data by pressing "ENTER"
        When I search data by pressing the "ENTER" key
        Then the results update accordingly

    Scenario: Apply filters that result in no data
        When I apply filters that result in no data
        Then a user-friendly message indicating no results found is displayed

    Scenario: Test removing filters to restore data visibility
        When I remove filters
        Then data visibility is restored

    Scenario: Check page responsiveness on different screen sizes
        Then the page is responsive and usable on desktop, tablet, and mobile devices

    Scenario: Ensure default tax year based on current date
        Given the current date is after April 1
        Then the default tax year is the current financial year
        Given the current date is before March 31
        Then the default tax year is the previous financial year

    Scenario: Select a different tax year and verify updates
        When I select a different tax year from the dropdown
        Then the results table updates to show data for the selected year

    Scenario: Ensure tax year dropdown displays relevant financial years
        Then the tax year dropdown displays a list of all relevant financial years

    Scenario: Ensure data accuracy for default and previous financial years
        Given the current date is after April 1
        Then the data displayed is accurate for the default tax year
        Given the current date is before March 31
        Then the data displayed is accurate for the previous financial year

    Scenario: Ensure selected tax year and data persist after refresh
        When I select a tax year and refresh the page
        Then the selected tax year and corresponding data persist

    Scenario: Expand/collapse penalty detail section
        When I click the expand/collapse icon next to an organization
        Then the penalty detail section expands/collapses correctly
        And the state is maintained after refreshing the page
        And multiple organizations' penalty details can be expanded/collapsed correctly

    Scenario: Ensure column data accuracy and format
        Then each column displays accurate data for each month
        And the data is displayed in the correct format (e.g., numerical values, percentages)
        And data displayed for each month is consistent and accurate

    Scenario: Ensure penalty assessments and total penalty amounts are correct
        Then penalty assessments are calculated correctly based on the provided data
        And total penalty amounts are calculated and displayed correctly

    Scenario: Test "Export Penalty Details" button
        When I click the "Export Penalty Details" button
        Then a detailed report in CSV format is generated
        And a detailed report in XLSX format is generated
        And the data in the exported report matches the data displayed on the screen

    Scenario: Test "Select All" checkbox and bulk actions menu
        When I use the "Select All" checkbox to select all organizations
        Then the bulk actions menu becomes active
        When I deselect all organizations
        Then the bulk actions menu is disabled
        When I select a few organizations manually
        Then the bulk actions menu becomes active
        When I deselect all manually selected organizations
        Then the bulk actions menu is disabled
        And the selection persists after navigating away and back to the page

    Scenario: Test "Select All" checkbox behavior with manual selections
        When I select a few organizations manually
        Then the "Select All" checkbox is not selected
        When I select all organizations manually
        Then the "Select All" checkbox is selected
        When I use the "Select All" checkbox to select all organizations and deselect one
        Then the "Select All" checkbox is deselected

    Scenario: Test "Export Summary" button
        When I click the "Export Summary" button
        Then a correct summary report in CSV format is generated
        And a correct summary report in XLSX format is generated
        And the data in the exported summary report matches the summary data displayed on the screen

    Scenario: Apply filter and test "Export Summary" button
        When I apply a filter and click the "Export Summary" button
        Then a summary report for the filtered data is generated

    Scenario: Test "Export Details" option
        When I click the "Export Details" option
        Then a detailed report in CSV format is generated
        And a detailed report in XLSX format is generated
        And the data in the exported detailed report matches the detailed data displayed on the screen

    Scenario: Apply filter and test "Export Details" option
        When I apply a filter and click the "Export Details" option
        Then a detailed report for the filtered data is generated

    Scenario: Test sorting functionality by column name
        When I sort the results by column name in ascending order
        Then the results are sorted in ascending order
        When I sort the results by column name in descending order
        Then the results are sorted in descending order
        And the sorting order persists after navigating away and returning to the page
        And the sorting order is maintained after updating the data
        When I apply a filter and sort the results by column name
        Then the sorting functionality works correctly with the filtered data
	
	