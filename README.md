# SuperShine Car Wash

## Project Description
The project description was to develop a self-service car wash as a command line app.
The whole project description is in the file 'CarWash.pdf'.

### Requirements:
- The car wash should use a WashCard as payment.
- Users should be able to pick these options:
    1. Chose wash.
    2. Recharge WashCard.
    3. Check amount left on WashCard.
- Should support 3 different types of car washes.
    - Some car washes should have a discount, within a specified time period.
- Should log all washes and WashCards(id and balance).
- Admin should be able to view sales statistics.

## Design and analysis
During the desing process i first created a Use Case Diagram which I used to create a domain model. 
During development I expanded on the domain model and in the end it evolved into a design class diagram of the final program.

*(Pictures of the diagrams are located in the Digrams folder)*

### Design Patterns
I actively used the **Command Pattern** to implement the options for the WashMachines menus. I used an abstract class `WashMachineOption`
with an abstract method `execute()` as a base. Each of the options extends this base class and implements the `execute()` method.

I also used the **Union Pattern** to implement the different wash types. Where each type extends an abstract super class.

## Commands
### Compile:
`~/CarWashSystem/src$ javac -d ../bin/ app/App.java`

### Run:
**User Mode:**`~/CarWashSystem/bin$ java app.App user`

**Admin Mode:**`~/CarWashSystem/bin$ java app.App admin`
