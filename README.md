# Poodle: Shift Scheduler

## Overview

### Project summary

Many handicapped people in Denmark need to schedule their own personal
helpers by collecting and organizing the employees' availabilities and generating
a schedule that satisfies each employee's availability, monthly shift quota, and
scheduling constraints such as that one employee cannot work two 24-hour shifts in a row.
This app solves the time-consuming and cognitively complex task of scheduling through a
two-pronged approach: 1.) deterministically eliminate all impossible assignments, thereby
greatly restricting the size of the set of all potential schedule permutations, and then
2.) exploratorily to try to find a path in a graph which satisfies the given constraints.

Include the link to your Devpost project page here: [Devpost](https://devpost.com/software/poodle-scheduler)

### Authors

* **Joseph Willem Ricci** - jwricci – jwricci@seas.upenn.edu – [GitHub](https://github.com/Joseph-Willem-Ricci)
* **Benjamin Lam** - lam-benjamin – benlam@seas.upenn.edu – [GitHub](https://github.com/lam-benjamin)

## Usage

This section walks a prospective user through the process of installing and running the project on their local machine. The more detailed and the more accurate, the better. User-friendly instructions will entice prospective users (including judges) to engage more deeply with your project, which could improve your hackathon score.

### Prerequisites

- Java
- any Java IDE

```
Provide code samples in this fenced code block.
```

### Installation

1. Clone the repository from GitHub
2. Open the project in your IDE and build the project

### Deployment

1. Edit `input.txt` according to the instructions to include the `name`, `quota` and `availabilities` of employees.
Any data must be included after the following line: 
```
INPUT STARTS HERE
```
 
2. Run `Main`. (By default the program would take in `input.txt` but you could also specify a different file path as an argument)

3. Read the error messages (if any) from the console and make relevant changes to the input file, or read the results.

## Additional information

### Tools used
* [JUnit](https://junit.org/junit4/) - For unit testing


### License

This package is licensed under the GNU General Public License v3.0 (<a href="https://choosealicense.com/licenses/gpl-3.0/" target="_blank">GPL-3</a>).
