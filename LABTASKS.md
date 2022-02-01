# Lab Tasks <!-- omit in toc -->

- [Notes on notation and structure](#notes-on-notation-and-structure)
- [Exploring the project](#exploring-the-project)
- [Exploring the server](#exploring-the-server)
- [Exploring the client](#exploring-the-client)
- [Use ZenHub to support Agile development](#use-zenhub-to-support-agile-development)
  - [Setting up the project ZenHub board](#setting-up-the-project-zenhub-board)
  - [Using the board](#using-the-board)
- [The epics/features](#the-epicsfeatures)
- [Questions](#questions)

## Notes on notation and structure

* Questions that you need to answer (as a group!) are indicated with question mark symbols (:question:).
* The [Questions](./LABTASKS.md#questions) section is at the end of this document.
* Tasks that specify work to do without a written response will be bulleted.

Responses to questions should be submitted as specified below (in the [QUESTIONS](./LABTASKS.md#questions)).

If you're ever confused about what you need to do for a given task, ask.
Similarly, if you're just not sure what's going on or what something does, or
how it does it, please ask! There's a _lot_ going on here, and if you're not
confused now and then you're probably not paying attention. :smile:

Before completing these lab tasks, make sure you have read through [`README.md`](./README.md) and completed the following:

* Set up your project [`README.md`](./README.md#setup)
* Run your server: [run configuration](./README.md#running-your-project)
* Run your server tests: [testing your server](./README.md#testing-your-project)

## Exploring the project

Look over the directory structure of the project before you start
making changes to it, and consider the various tools that we are
using to manage our project.

:question: Answer questions *1*, *2*, and *3* [QUESTIONS](./LABTASKS.md#questions)

## Exploring the server

Study the server (Java) code in the project you have cloned.
Run it according to the instructions in the
[README](./README.md), including running the JUnit tests. Answer
the following questions.

> Pro-tip: Using Google to gain additional knowledge or support your conjectures
about how something works is great! It's important that you think about how everything
fits together and works, though, so don't use Google as a replacement for
building your understanding or you will regret it!

:question: Answer questions *4* and *5* [QUESTIONS](./LABTASKS.md#questions)

Look at the tests in `server/src/test/java/umm3601.user` as they can
provide useful information about the intention of various
functions called by `Server` via the `UserController` class.

You should make sure you run the JUnit tests, and it would be
good to deliberately modify some of the tests and see what
happens when they break. (But make sure you restore them to
their passing state when you're done.)

:warning: We provide very solid testing for everything except for
the top level `Server` class, which is at the moment completely
untested. We think we could use
[functional testing in Javalin](https://javalin.io/tutorials/testing)
to provide coverage of the `Server` class, but we haven't tried that
yet. (You can, though. 😄)

## Exploring the client

The client resources are in `client`, which
contains the necessary HTML, CSS, and JavaScript files to
construct the _very_ simple client-side web app.

:question: Answer questions *6* and *7* and *8* [QUESTIONS](./LABTASKS.md#questions)

## Use ZenHub to support Agile development

We'll be using ZenHub to augment the standard GitHub issues
system with nifty powers to aid in Agile estimating,
planning, tracking, and development. The next two sections
describe the software development tasks you need to complete
for this lab, which all take the form of augmenting the server API with new functionality.

### Setting up the project ZenHub board

So before you actually start _coding_ on any of that, you
should spend some time using ZenHub to capture and estimate
stories and do some planning.

* Make sure you've turned on [ZenHub](http://zenhub.com) so it works :smile:
* Create at least one Milestone that starts on the first day of lab and ends when the lab is due
  * If you want to break the lab period into smaller time boxes and have multiple, smaller
    Milestones, that would be OK as well. If you do this, make sure you provide
    reasonable start and end dates so one follows on from the
    other, and the first starts at the beginning of the lab
    and the last ends when the lab is due.

Now you'll need to create several _epics_, one for each major feature described below.
(See ["Working with Epics inside GitHub"](https://www.zenhub.com/blog/working-with-epics-in-github/)
for info on creating and working with epics in ZenHub.) For each epic you should add the issues
(tasks) that you think you'll need to complete to provide a full version of this feature.

>In future labs and the project, implementing most epics will have at least two parts that
together "slice the cake":
>
>* Implementing the server-side functionality, e.g., adding support for a new API endpoint
     to the Javalin server code.
>* Adding the client-side functionality that allows users to access that new server-side work.
>
>Here, however, we've provided you with a fully functional client, so you won't do any
work on the client side in this lab. So you won't really slice the cake here, but you should
be aware that it will be important in the future for your stories to fully slice the cake.

:warning: One thing you should **not** do is create separate tasks for things like unit tests
or refactoring. Those activities should be "baked in" to your work flow, and not considered
separate (and therefore to some degree optional) activities.

Once you've created and estimated all the stories, you
should think about which ones you think you can reasonably
do in this lab. This could be all of them, but it doesn't
have to be. You can always add stories to this Milestone as
things progress, and in general customers would rather see
the set of stories you expect to complete in this Milestone
_increase_ rather than _decrease_, so being conservative in
your initial planning is probably a Good Thing.

* You should add the stories that you _expect_ to do in the lab to your Milestone.
* You should move the stories you expect to do into the Backlog track, and move all the
  other stories into the Icebox track.

### Using the board

You'll then need to keep an eye on your board throughout the
lab, using it to guide your decisions about what to work on,
updating issues as you make progress, etc.

Whenever you sit down to work on the project, you should be
clearly working on a specific story. If you feel like there's
something that _needs_ to be done but isn't in a story/issue, you
should make a story for that before you start working on it.

When you start work on a new story, you should create a
feature branch for that story, and commit your work on that
story to that branch. Commit messages should refer to that
story (by number, e.g., `Issue #8`) so GitHub can auto-link
the commits to that issue for you.

When you feel like a story is complete

* Move that card to the Review/QA track
* Issue a Pull Request (_we'll have to talk about this in lab_) from your feature branch onto your master branch

Then step away from that story for a while,
either by working on a different part of the lab, or by
doing something unrelated to Software Design. Then come back
back to that _as a team_ and review the requirements
described in the story and compare them to the functionality
you implemented. Is the story _done done_? Are there solid
and complete tests that back up the work? Can you break it?
Have you tried? Would you bet your career (or at least your
next raise) on this working in a customer demo or out in the
field?

If you find issues, document them, either in the existing
story, or through new stories. Then go back to working in
the feature branch for that story, and repeat the whole
process.

Once the story passes review, you should

* Merge the associated feature branch into master by accepting the (perhaps modified) pull request
* Move the story to the Done or Closed track as you see fit

## The epics/features

The initial server (Java) code demonstrates reading in a
collection of (randomly generated) user data, and making it
available (with filtering) via the simple API you explored above.
The client (JavaScript/HTML/CSS) demonstrates using simple forms
that allow the user to make requests of the server and see the results.

Your goal in this lab is to use test-driven development (TDD) to
extend the server's API to support serving 'to-do' data in such a way that it works with the provided client.
We **do** want you to write JUnit tests for the server functionality you
add, but you don't need to worry about the JavaScript code or how one
might test it. (We'll end up using some nice testing
tools that integrate with Angular.)

There is a file `data/todos.json` that has several hundred randomly
generated "to-do"s, each of which has:

* A unique `_id`
* An `owner`
* A `status` (which is a boolean - is the task completed or not)
* A `body` that describes the task
* A `category`

Below are the various features we'd like to see you implement in this lab. You should
create an epic for each of the features listed below, adding at issues as appropriate.

At the very least (necessary to get 85% of this part of the lab)
you should implement (and create meaningful server-side tests for) the following features:

* List all the todos
  * Implement an `api/todos` server-side endpoint, which returns all the to-dos
* List a single todo by ID
  * Implement an `api/todos/58895985c1849992336c219b` server-side endpoint, which
    returns the single todo with the given `_id`. It should return a 404
    (use the Javalin `NotFoundResponse` class) if there is no todo with the
    specified `_id`.
* Support limiting the number of todos that are displayed
  * Implement an `api/todos?limit=7` API endpoint, which lets you specify the maximum
    number of todos that the server returns.
* Support filtering todos by their status (either complete or incomplete)
  * Implement an `api/todos?status=complete` (or `incomplete`) endpoint which lets you
    filter the todos and only return the complete (or incomplete) ones
  * Note that the "database" stores the status as a boolean, but the endpoint uses
    "complete" and "incomplete". You'll have to implement the (simple) logic that
    transforms the endpoint "language" into the database terminology.
* Support searching for todos whose _bodies_ contain a given string
  * Implement an `api/todos?contains=banana` endpoint which lets you search for to-dos
    whose _bodies_ contain (anywhere) the given string (in this case "banana").

To get full (100%) credit on this part of the lab you should
implement (and create meaningful tests for) these additional features:

* Filter todos by owner
  * Implement the endpoint `api/todos?owner=Blanche` which returns just the to-dos
owned by Blanche
* Filter todos by category
  * Implement the endpoint `api/todos?category=groceries` which returns just the to-dos
in the `groceries` category
* Allow for ordering/sorting of todos by a particular attribute
  * Implement the endpoint `api/todos?orderBy=owner` (or `body`, `status`, or `category`)
    which sorts the returned to-dos alphabetically by the specified field

For full credit you also need to support arbitrary combinations
of these filters, e.g.,

```http
api/todos?owner=Blanche&status=complete&limit=12&orderBy=category
```

which would return the first 12 completed to-dos owned by
Blanche ordered by category.

Each of these if implemented properly should work in the provided client.

---

## Questions

Write up your answers to these questions in a Google Doc and turn that in via
Canvas on the assignment for this lab.

:bangbang:

* [ ] __Make sure that everyone in your group has edit privileges on the document.__
* [ ] __Make sure that the link you turn in gives us at least comment privileges.__
* [ ] __Include the URL of the GitHub repository for your group at the top of the
   GDoc. This will make it easier for us to figure out which team is "Snoozing Llamas".__
  
:bangbang: Make sure that your answers address the _purpose_ of
these tools. Don't just tell us _what_ something does, indicate
_why_ we'd want to have it.

:question: *1* What is the purpose of `.gitignore`?
([Maybe search for `.gitignore`?](https://www.google.com/search?q=.gitignore))

:question: *2* What role is Gradle playing in the
project, and what is the purpose of `build.gradle`?

:question: *3* What is the purpose of Github Actions?

:question: *4* Explain what an _endpoint_ is (also often called a _route_). (You might look at the
[Javalin](https://javalin.io/documentation#endpoint-handlers)
documentation for some help here.)

:question: *5* What is the purpose of `umm3601.Server` class?
What is the purpose of the `umm3601.user.UserController` class?
Explain what happens when a user accesses each of the
following URLs:

* :question: The page `users`
  * <http://localhost:4567/users.html>
* :question: The page `api/users`
  * <http://localhost:4567/api/users>
* :question: The page `api/users?age=25`
  * <http://localhost:4567/api/users?age=25>
* :question: The page `api/users/588935f5de613130e931ffd5`
  * <http://localhost:4567/api/users/588935f5de613130e931ffd5>

:bangbang: If you have your project running (see the README), these links should
actually work and generate results from your server.

:question: *6* What are the contents of the `client` folder? What is the purpose of each of the HTML files there?

:question: *7* Describe what happens when you filter users by
age in the client?

* What information is read from the web page, and where is it read from?
* What request is sent to the server?
* What reply does the server send back to the client? How is that constructed?
* What is received by the client, and how/where is it displayed?

:question: *8* Where is the client-side JavaScript defined? Name the HTML file(s) that
load and use it.
