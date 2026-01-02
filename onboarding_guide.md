# Onboarding Guide for New Developers

Welcome! This guide will help you build a strong foundation in backend development with our tech stack. Work through these topics sequentially, as each builds upon the previous one.

---

## Table of Contents
1. [Development Environment Setup](#1-development-environment-setup)
2. [Command Line Basics](#2-command-line-basics)
3. [Git Version Control](#3-git-version-control)
4. [Java Virtual Machine (JVM)](#4-java-virtual-machine-jvm)
5. [Object-Oriented Programming (OOP)](#5-object-oriented-programming-oop)
6. [Concurrency and IO in Backend Systems](#6-concurrency-and-io-in-backend-systems)
7. [SQL and Databases](#7-sql-and-databases)
8. [PostgreSQL Features and Internals](#8-postgresql-features-and-internals)
9. [Gradle Build Tool](#9-gradle-build-tool)
10. [Spring WebFlux](#10-spring-webflux)
11. [Project-Specific Setup](#11-project-specific-setup)
12. [Learning Resources](#12-learning-resources)

---

## 1. Development Environment Setup

### Required Tools
- **Java Development Kit (JDK)**: Version 17 or higher
- **IntelliJ IDEA**: Community or Ultimate edition
- **Git**: Version control system
- **PostgreSQL**: Database server (version 14+)
- **Docker**: For containerized services
- **Gradle**: Comes bundled with project (via wrapper)

### Installation Verification
```bash
# Check Java version
java -version

# Check Git
git --version

# Check Docker
docker --version

# Check PostgreSQL
psql --version
```

---

## 2. Command Line Basics

### Why the Command Line?

As developers, we interact with computers primarily through visual interfaces - clicking buttons, dragging windows, browsing folders. But there's a more powerful way to communicate with your machine: the **command line** (also called terminal, shell, or console).

Think of the command line as having a direct conversation with your computer using text commands. Instead of hunting through folders with your mouse, you type where you want to go. Instead of right-clicking to copy files, you type a command. It's faster, more precise, and can be automated.

**Real-world scenario:** Imagine you need to find all Java files in a large project that contain the word "deprecated". With GUI, you'd manually browse hundreds of folders. With command line, one command does it in seconds.

The command line is essential for:
- **Version control with Git**: All Git operations happen via command line
- **Build tools**: Gradle, Maven, npm all run from terminal
- **Server management**: Production servers rarely have GUI interfaces
- **Automation**: Scripts can chain commands together
- **Developer tools**: Debuggers, profilers, database clients

Let's start your journey from complete beginner to comfortable command-line user.

### Your First Steps: Understanding Where You Are

Every time you open a terminal, you're placed in a specific location (folder) on your computer. This is your **current working directory**.

**Scenario 1: Finding Your Location**

Open your terminal. You'll see a prompt (something like `$` or `>`). Now, let's find out where you are:

```bash
pwd
```

`pwd` means "print working directory". When you run this, you might see:
```
/Users/yourname
```

This is your **home directory** - your personal space on the computer. Think of it as your starting point.

**Understanding the output:**
- `/` is the root (top-level) of your computer
- `/Users` is a folder containing all user home directories
- `/Users/yourname` is your personal folder

### Scenario 2: Looking Around - What's Here?

Now that you know where you are, let's see what's in this location:

```bash
ls
```

You'll see a list of folders and files:
```
Desktop    Documents    Downloads    Pictures    workspace
```

Want more details? Let's ask for the "long" format:

```bash
ls -l
```

Now you see:
```
drwxr-xr-x  5 yourname  staff   160 Dec 17 10:30 Desktop
drwxr-xr-x  8 yourname  staff   256 Dec 17 09:15 Documents
-rw-r--r--  1 yourname  staff  1024 Dec 16 14:20 notes.txt
```

**Understanding the output:**
- First character `d` = directory (folder), `-` = file
- Numbers and dates show size and when it was modified
- Names are at the end

Want to see *everything*, including hidden files?

```bash
ls -la
```

Hidden files start with `.` (like `.gitignore`, `.bashrc`). They're usually configuration files.

**Tip:** You can combine commands. `ls -la` means "long format + show hidden files (all)".

### Scenario 3: Moving Around - Changing Directories

You're a developer joining a new project. First task: Navigate to the project folder.

```bash
cd Documents
```

`cd` means "change directory". You've just moved into the Documents folder.

Check where you are now:
```bash
pwd
```
Output: `/Users/yourname/Documents`

Let's say your project is inside a `workspace` folder:

```bash
cd workspace/lending-platform-service
```

Check again:
```bash
pwd
```
Output: `/Users/yourname/Documents/workspace/lending-platform-service`

**Going back up:** Made a mistake? Go back one level:

```bash
cd ..
```

The `..` means "parent directory" (one level up).

**Shortcut to home:** From anywhere, jump straight to your home directory:

```bash
cd ~
```

Or simply:
```bash
cd
```

**Pro tip:** Use Tab key for auto-completion. Type `cd Doc` and press Tab - it completes to `cd Documents/`.

### Scenario 4: Creating Your Workspace

Let's create a folder for practice:

```bash
mkdir learning-kotlin
```

`mkdir` means "make directory". This creates a new folder called `learning-kotlin`.

Verify it exists:
```bash
ls
```

Navigate into it:
```bash
cd learning-kotlin
```

Create nested folders in one command:
```bash
mkdir -p src/main/kotlin
```

The `-p` flag creates parent directories as needed (src, then main inside it, then kotlin inside main).

### Scenario 5: Working with Files

**Creating a file:**

You want to create a quick note:

```bash
echo "My first Kotlin project" > README.md
```

This creates a file called `README.md` with the text "My first Kotlin project".

**Viewing file contents:**

Quick peek at small files:
```bash
cat README.md
```

Output: `My first Kotlin project`

For larger files, use `less` for scrolling:
```bash
less build.gradle.kts
```
(Press `q` to quit, Space to scroll down, `b` to scroll up)

**Viewing just the beginning or end:**
```bash
head -n 10 logfile.txt    # First 10 lines
tail -n 20 logfile.txt    # Last 20 lines
tail -f application.log   # Follow new lines as they're added (great for logs!)
```

### Scenario 6: Searching Inside Files

**Real-world problem:** You need to find where "UserService" is used in your codebase.

```bash
grep "UserService" src/main/kotlin/UserController.kt
```

This searches for "UserService" in UserController.kt and shows matching lines.

Search recursively through all files in a directory:
```bash
grep -r "UserService" src/
```

Case-insensitive search:
```bash
grep -ri "userservice" src/
```

Show line numbers:
```bash
grep -rn "UserService" src/
```

**Example output:**
```
src/main/kotlin/UserController.kt:12:class UserController(private val userService: UserService)
src/main/kotlin/UserService.kt:8:class UserService(private val repository: UserRepository)
```

### Scenario 7: Copying and Moving Files

**Copying a configuration file:**

```bash
cp application.yml application-backup.yml
```

This creates a copy. Original file remains unchanged.

**Copying entire directories:**
```bash
cp -r src/ src-backup/
```

The `-r` means "recursive" (copy everything inside, including subdirectories).

**Moving/Renaming files:**

```bash
mv oldname.txt newname.txt
```

This renames the file. Or move it to another location:

```bash
mv README.md docs/
```

### Scenario 8: Deleting Files (Be Careful!)

**Important:** There's no "Recycle Bin" in command line. Deleted = gone forever.

Delete a file:
```bash
rm unwanted.txt
```

Delete an empty directory:
```bash
rmdir empty-folder
```

Delete a directory and all its contents:
```bash
rm -r old-project/
```

**Safety tip:** Use `-i` flag for confirmation:
```bash
rm -i important.txt
```

It will ask: `remove important.txt? (y/n)` before deleting.

### Scenario 9: Managing Running Processes

**Problem:** Your application is running and you need to stop it.

First, find the process:
```bash
ps aux | grep java
```

This lists all processes and filters for ones containing "java".

Output might show:
```
yourname  12345  2.5  1.2  java -jar myapp.jar
```

The number `12345` is the **process ID (PID)**.

Stop it gracefully:
```bash
kill 12345
```

If it won't stop, force it:
```bash
kill -9 12345
```

**Finding what's using a port:**

Your app won't start because "port 8080 is already in use". Find the culprit:

```bash
lsof -i :8080
```

Output shows which process is using port 8080. Note its PID and kill it.

### Scenario 10: Combining Commands (Pipes and Redirection)

**The power of pipes:** Chain commands together using `|`

Find all Kotlin files and count them:
```bash
find . -name "*.kt" | wc -l
```

Search for errors in logs and save to file:
```bash
grep "ERROR" application.log > errors.txt
```

Append to existing file:
```bash
grep "WARNING" application.log >> errors.txt
```

### Hands-On Practice Mission

**Your task:** Set up a project structure and explore it.

```bash
# 1. Navigate to your home directory
cd ~

# 2. Create a project structure
mkdir -p dev-learning/my-first-project/src/main/kotlin

# 3. Navigate into the project
cd dev-learning/my-first-project

# 4. Create a README
echo "# My First Project" > README.md

# 5. Create a Kotlin file
echo "fun main() { println(\"Hello!\") }" > src/main/kotlin/Main.kt

# 6. Verify the structure
ls -la
ls -la src/main/kotlin/

# 7. View your Kotlin file
cat src/main/kotlin/Main.kt

# 8. Search for the word "main" in your project
grep -r "main" .

# 9. Find all .kt files
find . -name "*.kt"

# 10. Clean up (optional)
cd ~
rm -rf dev-learning
```

**What you learned:**
- `pwd`: Where am I?
- `ls`: What's here?
- `cd`: Go somewhere else
- `mkdir`: Create folders
- `echo` and `>`: Create files
- `cat`: View files
- `grep`: Search inside files
- `find`: Search for files by name
- `rm`: Delete (carefully!)
- `|`: Chain commands

### Quick Reference Card

Keep this handy:

| Command | What it does | Example |
|---------|-------------|---------|
| `pwd` | Show current location | `pwd` |
| `ls` | List files | `ls -la` |
| `cd <dir>` | Change directory | `cd workspace` |
| `cd ..` | Go up one level | `cd ..` |
| `cd ~` | Go to home | `cd ~` |
| `mkdir <dir>` | Create directory | `mkdir project` |
| `touch <file>` | Create empty file | `touch notes.txt` |
| `cat <file>` | View file | `cat README.md` |
| `less <file>` | View file (scrollable) | `less log.txt` |
| `grep "text" <file>` | Search in file | `grep "error" log.txt` |
| `cp <src> <dst>` | Copy | `cp file.txt backup.txt` |
| `mv <src> <dst>` | Move/rename | `mv old.txt new.txt` |
| `rm <file>` | Delete file | `rm temp.txt` |
| `rm -r <dir>` | Delete directory | `rm -r old-project` |
| `ps aux` | List processes | `ps aux \| grep java` |
| `kill <pid>` | Stop process | `kill 12345` |
| `lsof -i :<port>` | Check port usage | `lsof -i :8080` |

### Next Steps

You now have the fundamental skills to navigate and manipulate your file system via command line. In the next section, you'll use these skills extensively with **Git** - where command line truly shines.

---

## 3. Git Version Control

### Understanding Version Control

Consider working on a document: you make changes, realize they don't work, and want to go back. With code spanning 50 files and multiple developers working simultaneously, managing versions becomes critical.

Git is a version control system that tracks changes to your codebase:
- When you add a feature that breaks functionality, you can revert to a working state
- When two developers modify the same file, Git helps merge their changes
- When you need to see what changed in a release, Git shows exact differences
- When something breaks in production, Git shows what changed and when

### The Problem: Managing Code Versions

Without version control, teams often resort to folder copying:

```
my-project-v1.zip
my-project-v2-final.zip
my-project-v2-final-updated.zip
my-project-v3-johns-changes.zip
my-project-v3-merged.zip
```

This approach has several problems:
- Unclear which version is current
- Difficult to merge changes from multiple developers
- No record of what changed or why
- Risk of losing work if folders are deleted

Git solves these problems by:
- Maintaining one codebase with complete history
- Enabling parallel development with branches
- Tracking every change with context
- Providing a distributed backup system

### Core Concepts

Before diving into commands, understand these four concepts:

#### 1. Repository (Repo)
A project folder that Git tracks. Contains your code plus a hidden `.git` folder with complete history.

A repository stores every version of your code. You can view any previous state or compare changes across time.

#### 2. Commit
A snapshot of your entire project at a specific point in time. Each commit records:
- A unique ID (hash): `a4f3d91`
- A message describing the change: "Fix user login bug"
- Author and timestamp
- What changed from the previous commit

Commits form a timeline of your project's evolution. You can always return to any commit to see or restore that version.

#### 3. Branch
An independent line of development. The main branch contains stable code. Feature branches let you develop new functionality without affecting the main codebase. When your feature is complete, you merge it back to main.

```
main:       A---B---C---F---G
                 \       /
feature:          D-----E
```

- Main branch has commits A, B, C (stable, working code)
- You create a branch at B to develop a feature (commits D, E)
- When ready, merge back to main (F merges your feature)
- Main continues (G)

#### 4. Remote
A server storing your repository (like GitHub, GitLab). Your laptop has a local copy, your teammates have their copies, and the remote server hosts the central version that everyone synchronizes with.

### Learning Git: Step by Step

#### Getting Started: Joining a Project

**Scenario:** You just joined a company. They give you a repository URL. Time to get the code!

**Step 1: Configure your identity**

Git needs to know who you are for commit history:

```bash
git config --global user.name "Your Name"
git config --global user.email "your.email@company.com"
```

This is a one-time setup. Verify it worked:

```bash
git config --global --list
```

**Step 2: Clone the repository**

```bash
git clone https://github.com/company/lending-platform-service.git
```

**What just happened:**
- Git downloaded the entire project + complete history
- Created a folder called `lending-platform-service`
- Set up connection to remote server (called "origin")

Navigate into it:
```bash
cd lending-platform-service
```

**Step 3: Understand what you have**

Check which branch you're on:
```bash
git branch
```

Output: `* main` (the asterisk shows your current branch)

See the commit history:
```bash
git log --oneline
```

Output shows commits:
```
a4f3d91 Fix user login bug
b8e2c45 Add payment processing
c9f1a23 Initial project setup
```

Each line represents one commit in the project's history.

#### Making Your First Change

**Scenario:** You need to fix a typo in README.md. Here's the complete workflow:

**Step 1: Check status (always start here!)**

```bash
git status
```

Output tells you:
```
On branch main
Your branch is up to date with 'origin/main'.

nothing to commit, working tree clean
```

Translation: You're on main branch, no changes yet.

**Step 2: Create a branch for your change**

**Why?** Never work directly on `main`. Main is the stable version. You work on a branch, get it reviewed, then merge.

```bash
git checkout -b fix/readme-typo
```

This creates AND switches to a new branch called `fix/readme-typo`.

Verify:
```bash
git branch
```
Output:
```
* fix/readme-typo
  main
```

**Step 3: Make your change**

Open README.md, fix the typo, save.

**Step 4: See what changed**

```bash
git status
```

Output:
```
On branch fix/readme-typo
Changes not staged for commit:
  modified:   README.md
```

Git detected the change! See the actual differences:

```bash
git diff
```

This shows line-by-line what changed:
```diff
- This is a leding platform
+ This is a lending platform
```

**Step 5: Stage your changes**

"Staging" means "I want to include this in my next commit".

```bash
git add README.md
```

Or stage everything:
```bash
git add .
```

Check status again:
```bash
git status
```

Output:
```
On branch fix/readme-typo
Changes to be committed:
  modified:   README.md
```

The file is now **staged** (ready to commit).

**Step 6: Commit (take the snapshot)**

```bash
git commit -m "Fix typo in README: leding -> lending"
```

**Anatomy of a good commit message:**
- Start with verb (Fix, Add, Update, Remove)
- Be specific about what changed
- Keep it under 50 characters for summary
- For longer explanations, add a blank line and more details

Check history:
```bash
git log --oneline
```

You'll see your commit on top!

**Step 7: Push to remote**

Your commit exists on your laptop. Push it to the server so others can see:

```bash
git push origin fix/readme-typo
```

**Translation:** "Push my branch `fix/readme-typo` to the remote server called `origin`".

First time pushing this branch? Git might show:
```bash
git push --set-upstream origin fix/readme-typo
```

This links your local branch to the remote one.

#### Getting Latest Changes

**Scenario:** Your team has pushed updates to `main`. You need to pull them into your local repository.

**Step 1: Switch to main**

```bash
git checkout main
```

**Step 2: Pull latest changes**

```bash
git pull origin main
```

**What happened:**
- Git downloaded new commits from remote
- Updated your local `main` branch
- Your files now reflect latest code

**Pro tip:** Always pull before starting work to avoid conflicts.

#### Handling Merge Conflicts

**Scenario:** You edited `UserService.kt`. Meanwhile, your teammate also edited the same file and merged to main. Now you try to pull...

```bash
git pull origin main
```

**Conflict!**
```
CONFLICT (content): Merge conflict in UserService.kt
Automatic merge failed; fix conflicts and then commit the result.
```

Don't panic! Here's what to do:

**Step 1: See which files have conflicts**

```bash
git status
```

Output shows:
```
Unmerged paths:
  both modified:   UserService.kt
```

**Step 2: Open the file**

You'll see conflict markers:

```kotlin
fun processPayment(amount: Double) {
<<<<<<< HEAD
    // Your version
    validateAmount(amount)
=======
    // Teammate's version
    checkPaymentLimit(amount)
>>>>>>> origin/main
    // Rest of the code
}
```

**Understanding the markers:**
- `<<<<<<< HEAD`: Start of your changes
- `=======`: Separator
- `>>>>>>> origin/main`: Start of their changes

**Step 3: Resolve manually**

Decide what to keep. Maybe you need both:

```kotlin
fun processPayment(amount: Double) {
    // Keep both validations
    validateAmount(amount)
    checkPaymentLimit(amount)
    // Rest of the code
}
```

Delete the conflict markers (`<<<<<<<`, `=======`, `>>>>>>>`).

**Step 4: Mark as resolved**

```bash
git add UserService.kt
```

**Step 5: Complete the merge**

```bash
git commit -m "Merge main and resolve conflict in UserService"
```

Done! The conflict is resolved.

#### Common Workflows

**Workflow 1: Feature Development**

```bash
# 1. Start from updated main
git checkout main
git pull origin main

# 2. Create feature branch
git checkout -b feature/add-user-dashboard

# 3. Work, work, work... make changes

# 4. Stage and commit regularly
git add .
git commit -m "Add user dashboard UI"

# ... more work ...

git add .
git commit -m "Connect dashboard to backend API"

# 5. Push when ready
git push origin feature/add-user-dashboard

# 6. Create Pull Request on GitHub/GitLab for review
```

**Workflow 2: Quick Bug Fix**

```bash
# 1. Create bugfix branch
git checkout -b bugfix/fix-login-error

# 2. Make fix

# 3. Commit
git add .
git commit -m "Fix login error when email is empty"

# 4. Push
git push origin bugfix/fix-login-error
```

**Workflow 3: Viewing History**

```bash
# Simple log
git log --oneline

# Detailed log with graph
git log --oneline --graph --all

# See what changed in a commit
git show a4f3d91

# See changes in last 3 commits
git log -3 -p

# Find commits by author
git log --author="John"

# Find commits by message keyword
git log --grep="login"
```

**Workflow 4: Undoing Mistakes**

**Undo uncommitted changes:**
```bash
# Discard changes in one file
git checkout -- UserService.kt

# Discard all changes
git checkout -- .
```

**Undo last commit (but keep changes):**
```bash
git reset --soft HEAD~1
```

**Undo last commit (discard changes):**
```bash
git reset --hard HEAD~1
```

**Undo a pushed commit (safe way):**
```bash
git revert a4f3d91
```

This creates a new commit that undoes the specified commit.

### Understanding Branches Visually

```
main:          A---B---C-----------F---G---H
                    \             /
feature-1:           D---E-------/
                        \
feature-2:               I---J
```

- `main`: Stable production code
- `feature-1`: Someone's working on feature 1 (D, E), merged at F
- `feature-2`: You branched from `feature-1` at E, working on feature 2 (I, J)

**Creating and switching branches:**

```bash
# Create and switch in one command
git checkout -b feature/my-feature

# Or separately
git branch feature/my-feature
git checkout feature/my-feature

# Modern way (Git 2.23+)
git switch -c feature/my-feature  # create and switch
git switch main                   # just switch
```

**Deleting branches:**

```bash
# Delete local branch (safe - prevents if unmerged)
git branch -d feature/old-feature

# Force delete
git branch -D feature/old-feature

# Delete remote branch
git push origin --delete feature/old-feature
```

### The .gitignore File

Some files shouldn't be tracked: build artifacts, IDE settings, secrets.

Create `.gitignore` in project root:

```
# Build outputs
build/
*.class
*.jar

# IDE settings
.idea/
.vscode/

# OS files
.DS_Store
Thumbs.db

# Secrets
.env
credentials.json

# Logs
*.log
```

Files matching these patterns are ignored by Git.

### Best Practices

1. **Commit Often, Push Regularly**
   - Commit every logical change (not every keystroke!)
   - Push at least daily
   - Think: "If my laptop dies now, how much do I lose?"

2. **Write Good Commit Messages**
   ```
   Good:
   - "Fix null pointer exception in payment processing"
   - "Add user authentication middleware"
   - "Refactor database connection pooling"

   Bad:
   - "fix bug"
   - "changes"
   - "asdf"
   - "Final fix (for real this time)"
   ```

3. **Branch Naming Conventions**
   - `feature/add-payment-gateway`
   - `bugfix/fix-login-timeout`
   - `hotfix/critical-security-patch`
   - `refactor/simplify-user-service`

4. **Never Commit Secrets**
   - No passwords, API keys, tokens
   - Use environment variables instead
   - If you accidentally commit secrets, they're in history forever (even if you delete them)
   - Use `.gitignore` to prevent this

5. **Pull Before Push**
   - Always `git pull` before `git push`
   - Avoids conflicts and rejected pushes

6. **One Feature Per Branch**
   - Don't mix unrelated changes
   - Makes code review easier
   - Easier to revert if needed

### Your Daily Git Routine

```bash
# Morning: Start your day
git checkout main
git pull origin main
git checkout -b feature/your-task

# During work: Commit frequently
# ... make changes ...
git add .
git commit -m "Implement user validation"

# ... more changes ...
git add .
git commit -m "Add tests for user validation"

# End of day: Push your work
git push origin feature/your-task

# Next day: Get latest, continue work
git checkout main
git pull origin main
git checkout feature/your-task
git merge main  # Get latest main into your branch

# When done: Push and create Pull Request
git push origin feature/your-task
# Then create PR on GitHub/GitLab
```

### Quick Reference: Commands You'll Use Daily

| What you want to do | Command |
|---------------------|---------|
| Get project code | `git clone <url>` |
| See current status | `git status` |
| Create new branch | `git checkout -b feature/name` |
| Switch branches | `git checkout branch-name` |
| See what changed | `git diff` |
| Stage changes | `git add .` |
| Commit changes | `git commit -m "message"` |
| Push to remote | `git push origin branch-name` |
| Get latest changes | `git pull origin main` |
| See history | `git log --oneline` |
| Discard local changes | `git checkout -- .` |
| See all branches | `git branch -a` |

### Next Steps

You now understand version control and can work with Git confidently. Next, we'll learn about the **JVM** - the engine that runs your Kotlin code. Understanding the JVM will make you a better developer by helping you understand how your code actually executes.

---

## 4. Java Virtual Machine (JVM)

### What is the JVM?

The JVM is a virtual machine that runs Java bytecode. It provides:
- **Platform independence**: "Write once, run anywhere"
- **Memory management**: Automatic garbage collection
- **Security**: Sandboxed execution environment
- **Performance**: Just-in-time (JIT) compilation

### JVM Architecture

```
┌─────────────────────────────────────┐
│      Java Source Code (.java)       │
└──────────────┬──────────────────────┘
               │ javac (compiler)
               ▼
┌─────────────────────────────────────┐
│      Bytecode (.class files)        │
└──────────────┬──────────────────────┘
               │ JVM
               ▼
┌─────────────────────────────────────┐
│           JVM Components            │
│  ┌────────────────────────────┐    │
│  │    Class Loader Subsystem   │    │
│  └────────────────────────────┘    │
│  ┌────────────────────────────┐    │
│  │     Runtime Data Areas      │    │
│  │  - Heap                     │    │
│  │  - Stack                    │    │
│  │  - Method Area              │    │
│  └────────────────────────────┘    │
│  ┌────────────────────────────┐    │
│  │    Execution Engine         │    │
│  │  - Interpreter              │    │
│  │  - JIT Compiler             │    │
│  │  - Garbage Collector        │    │
│  └────────────────────────────┘    │
└─────────────────────────────────────┘
```

### Memory Areas

#### Heap
- Stores objects and class instances
- Shared among all threads
- Garbage collected
- Can cause `OutOfMemoryError` if full

#### Stack
- Stores method frames (local variables, method calls)
- Each thread has its own stack
- LIFO structure
- Can cause `StackOverflowError` if too deep

#### Method Area (Metaspace in Java 8+)
- Stores class metadata, static variables, constants
- Shared among all threads

### Garbage Collection

The JVM automatically reclaims memory from objects no longer in use.

**Common GC Algorithms:**
- **Serial GC**: Single-threaded, good for small applications
- **Parallel GC**: Multi-threaded, good for throughput
- **G1 GC**: Default in Java 9+, balances throughput and latency
- **ZGC**: Low-latency GC for large heaps

**GC Monitoring:**
```bash
# Run with GC logging
java -Xlog:gc* -jar application.jar

# Set heap size
java -Xms512m -Xmx2g -jar application.jar
```

### JVM Languages

The JVM runs multiple languages:
- Java
- Kotlin (what we use!)
- Scala
- Groovy
- Clojure

All compile to JVM bytecode.

### Key Concepts to Remember
1. **Bytecode**: Intermediate representation between source and machine code
2. **JIT Compilation**: Converts frequently-used bytecode to native machine code for performance
3. **Garbage Collection**: Automatic memory management
4. **Class Loading**: Dynamic loading of classes as needed

---

## 5. Object-Oriented Programming (OOP)

### Why Object-Oriented Programming?

Early programming was procedural: write functions that manipulate data. As programs grew larger, this became difficult to manage. OOP organizes code around "objects" that combine data and the functions that operate on that data.

Consider building a banking system. Without OOP:
- Data (account balance, customer name) lives separately from functions (deposit, withdraw)
- No clear boundaries - any function can modify any data
- Difficult to reason about complex systems

With OOP:
- A `BankAccount` object contains its own data (balance) and methods (deposit, withdraw)
- Clear boundaries - only the account's own methods can modify its balance
- Easier to model real-world concepts

### The Four Pillars

#### 1. Encapsulation

**Concept**: Bundle data and the methods that operate on that data together. Hide internal details and expose only what's necessary.

**Why it matters**: Prevents external code from breaking your object's internal state. You control how data is accessed and modified.

**Java Implementation:**
```java
public class BankAccount {
    private double balance;  // Private - cannot be accessed directly

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public double getBalance() {
        return balance;
    }
}

// Usage
BankAccount account = new BankAccount(1000.0);
account.deposit(500.0);
// account.balance = -1000;  // ERROR: balance is private
```

**Kotlin Implementation:**
```kotlin
class BankAccount(private var balance: Double) {

    fun deposit(amount: Double) {
        if (amount > 0) {
            balance += amount
        }
    }

    fun getBalance(): Double = balance
}

// Usage
val account = BankAccount(1000.0)
account.deposit(500.0)
// account.balance = -1000.0  // ERROR: balance is private
```

**Key differences**:
- Kotlin: Constructor and property declaration combined
- Kotlin: No need for explicit getter/setter boilerplate
- Kotlin: `val` (immutable) vs `var` (mutable) built into language

#### 2. Inheritance

**Concept**: Create new classes based on existing classes. The new class inherits properties and methods from the parent class.

**Why it matters**: Reuse code and establish relationships. A `SavingsAccount` is a type of `BankAccount` - it inherits common behavior but can add specific features.

**When to use**: When there's a clear "is-a" relationship. A Dog *is an* Animal. A SavingsAccount *is a* BankAccount.

**Java Implementation:**
```java
// Base class
public class Animal {
    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    public void makeSound() {
        System.out.println("Some generic sound");
    }
}

// Derived class
public class Dog extends Animal {
    public Dog(String name) {
        super(name);  // Call parent constructor
    }

    @Override
    public void makeSound() {
        System.out.println("Woof!");
    }

    public void fetch() {
        System.out.println(name + " is fetching");
    }
}

// Usage
Animal animal = new Animal("Generic");
animal.makeSound();  // "Some generic sound"

Dog dog = new Dog("Buddy");
dog.makeSound();     // "Woof!"
dog.fetch();         // "Buddy is fetching"
```

**Kotlin Implementation:**
```kotlin
// Base class - must be marked 'open' to allow inheritance
open class Animal(val name: String) {
    open fun makeSound() {  // 'open' allows overriding
        println("Some generic sound")
    }
}

// Derived class
class Dog(name: String) : Animal(name) {
    override fun makeSound() {
        println("Woof!")
    }

    fun fetch() {
        println("$name is fetching")
    }
}

// Usage
val animal = Animal("Generic")
animal.makeSound()  // "Some generic sound"

val dog = Dog("Buddy")
dog.makeSound()     // "Woof!"
dog.fetch()         // "Buddy is fetching"
```

**Key differences**:
- Kotlin: Classes are `final` by default - must mark `open` to allow inheritance
- Kotlin: Methods are `final` by default - must mark `open` to allow overriding
- Kotlin: String interpolation with `$name`
- Java: Everything inheritable by default (unless marked `final`)

#### 3. Polymorphism

**Concept**: The ability to treat objects of different types uniformly when they share a common interface. "Many forms" - same method call, different behavior.

**Why it matters**: Write generic code that works with multiple types. A payment processor doesn't care if it's processing credit card or UPI - it just calls `processPayment()`.

**Java Implementation:**
```java
// Interface
public interface PaymentProcessor {
    void processPayment(double amount);
}

// Implementation 1
public class CreditCardProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing $" + amount + " via credit card");
        // Credit card specific logic
    }
}

// Implementation 2
public class UPIProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing $" + amount + " via UPI");
        // UPI specific logic
    }
}

// Generic function - works with any PaymentProcessor
public void charge(PaymentProcessor processor, double amount) {
    processor.processPayment(amount);
}

// Usage
PaymentProcessor ccProcessor = new CreditCardProcessor();
PaymentProcessor upiProcessor = new UPIProcessor();

charge(ccProcessor, 100.0);   // "Processing $100.0 via credit card"
charge(upiProcessor, 100.0);  // "Processing $100.0 via UPI"
```

**Kotlin Implementation:**
```kotlin
// Interface
interface PaymentProcessor {
    fun processPayment(amount: Double)
}

// Implementation 1
class CreditCardProcessor : PaymentProcessor {
    override fun processPayment(amount: Double) {
        println("Processing $$amount via credit card")
        // Credit card specific logic
    }
}

// Implementation 2
class UPIProcessor : PaymentProcessor {
    override fun processPayment(amount: Double) {
        println("Processing $$amount via UPI")
        // UPI specific logic
    }
}

// Generic function - works with any PaymentProcessor
fun charge(processor: PaymentProcessor, amount: Double) {
    processor.processPayment(amount)
}

// Usage
val ccProcessor: PaymentProcessor = CreditCardProcessor()
val upiProcessor: PaymentProcessor = UPIProcessor()

charge(ccProcessor, 100.0)   // "Processing $100.0 via credit card"
charge(upiProcessor, 100.0)  // "Processing $100.0 via UPI"
```

**Key differences**:
- Very similar in both languages
- Kotlin: Cleaner syntax, no semicolons
- Kotlin: Type inference - `val processor = CreditCardProcessor()` works too

**Types of Polymorphism:**
- **Compile-time (Overloading)**: Multiple methods with same name, different parameters
- **Runtime (Overriding)**: Subclass provides specific implementation of parent method

#### 4. Abstraction

**Concept**: Hide implementation complexity and show only essential features. Define what something does without specifying how it does it.

**Why it matters**: Focus on what an object does, not how. A `Shape` has an area - circle calculates it one way, rectangle another way. Users of `Shape` don't need to know the formula.

**Java Implementation:**
```java
// Abstract class - cannot be instantiated directly
public abstract class Shape {
    // Abstract method - no implementation
    public abstract double calculateArea();

    // Concrete method - has implementation
    public void display() {
        System.out.println("Area: " + calculateArea());
    }
}

// Concrete class - must implement abstract methods
public class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

public class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }
}

// Usage
Shape circle = new Circle(5.0);
circle.display();  // "Area: 78.54"

Shape rectangle = new Rectangle(4.0, 6.0);
rectangle.display();  // "Area: 24.0"

// Shape shape = new Shape();  // ERROR: Cannot instantiate abstract class
```

**Kotlin Implementation:**
```kotlin
// Abstract class
abstract class Shape {
    // Abstract method
    abstract fun calculateArea(): Double

    // Concrete method
    fun display() {
        println("Area: ${calculateArea()}")
    }
}

// Concrete class
class Circle(private val radius: Double) : Shape() {
    override fun calculateArea(): Double = Math.PI * radius * radius
}

class Rectangle(
    private val width: Double,
    private val height: Double
) : Shape() {
    override fun calculateArea(): Double = width * height
}

// Usage
val circle: Shape = Circle(5.0)
circle.display()  // "Area: 78.54"

val rectangle: Shape = Rectangle(4.0, 6.0)
rectangle.display()  // "Area: 24.0"

// val shape = Shape()  // ERROR: Cannot instantiate abstract class
```

**Key differences**:
- Kotlin: Single-expression functions can use `=` syntax
- Kotlin: Properties in constructor
- Both: Abstract classes can have concrete methods

### SOLID Principles

SOLID is an acronym for five design principles that make software more maintainable and flexible. These principles help you write code that's easier to understand, test, and modify.

#### S - Single Responsibility Principle

**Principle**: A class should have only one reason to change. Each class should do one thing and do it well.

**Why it matters**: When a class has multiple responsibilities, changes to one responsibility can break the others. A class with a single, well-defined purpose is easier to understand, test, and maintain.

**When to apply**: When you find yourself saying "this class handles X *and* Y *and* Z", it's a sign to split responsibilities.

**Problem scenario**: You have a `User` class that handles data, database operations, email sending, and report generation. When the email format changes, you need to modify the `User` class. When the database changes, you modify `User` again. The class becomes bloated and fragile.

```kotlin
// Violates SRP - User class has too many responsibilities
class User(
    val id: String,
    val name: String,
    val email: String
) {
    fun saveToDatabase() {
        // Database logic here
        println("Saving to database...")
    }

    fun sendWelcomeEmail() {
        // Email sending logic
        println("Sending email to $email...")
    }

    fun generateReport(): String {
        // Report generation logic
        return "User Report for $name"
    }
}
```

**Solution**: Separate concerns into different classes. Each class has one clear responsibility.

```kotlin
// Follows SRP - Each class has a single responsibility

// 1. User - just holds data
data class User(
    val id: String,
    val name: String,
    val email: String
)

// 2. UserRepository - handles database operations
class UserRepository {
    fun save(user: User) {
        println("Saving ${user.name} to database...")
        // Database logic here
    }

    fun findById(id: String): User? {
        // Database query logic
        return null
    }
}

// 3. EmailService - handles email operations
class EmailService {
    fun sendWelcomeEmail(user: User) {
        println("Sending welcome email to ${user.email}...")
        // Email sending logic
    }
}

// 4. ReportGenerator - handles report generation
class ReportGenerator {
    fun generate(user: User): String {
        return "User Report for ${user.name}"
    }
}
```

Now when email logic changes, you only touch `EmailService`. When database changes, you only touch `UserRepository`.

#### O - Open/Closed Principle

**Principle**: Software entities should be open for extension but closed for modification. You should be able to add new functionality without changing existing code.

**Why it matters**: Changing existing, working code risks introducing bugs. Better to extend functionality through new code rather than modifying tested code.

**When to apply**: When you find yourself modifying the same class repeatedly to add new variants or behaviors.

**Problem scenario**: You're building an e-commerce system. Initially, you support only seasonal discounts. Later, you need VIP discounts, bulk discounts, referral discounts. Each time, you modify the existing discount calculation method, risking bugs.

```kotlin
// Violates OCP - Must modify this class for each new discount type
class DiscountCalculator {
    fun calculateDiscount(amount: Double, discountType: String): Double {
        return when (discountType) {
            "SEASONAL" -> amount * 0.1
            "VIP" -> amount * 0.2
            "BULK" -> amount * 0.15
            // Need to modify this method every time a new discount type is added
            else -> 0.0
        }
    }
}
```

**Solution**: Use interfaces and polymorphism. Add new discount types by creating new classes, not modifying existing ones.

```kotlin
// Follows OCP - Can add new discount types without modifying existing code

interface DiscountStrategy {
    fun calculateDiscount(amount: Double): Double
}

class SeasonalDiscount : DiscountStrategy {
    override fun calculateDiscount(amount: Double) = amount * 0.1
}

class VIPDiscount : DiscountStrategy {
    override fun calculateDiscount(amount: Double) = amount * 0.2
}

class BulkDiscount : DiscountStrategy {
    override fun calculateDiscount(amount: Double) = amount * 0.15
}

// Add new discount types by creating new classes (extension)
class ReferralDiscount : DiscountStrategy {
    override fun calculateDiscount(amount: Double) = amount * 0.25
}

// The calculator doesn't change when adding new discount types
class PriceCalculator(private val discountStrategy: DiscountStrategy) {
    fun calculateFinalPrice(originalPrice: Double): Double {
        val discount = discountStrategy.calculateDiscount(originalPrice)
        return originalPrice - discount
    }
}
```

#### L - Liskov Substitution Principle

**Principle**: Objects of a superclass should be replaceable with objects of a subclass without breaking the application. Subclasses must honor the contract established by the parent class.

**Why it matters**: Prevents subtle bugs where substituting a subclass breaks expectations. Ensures inheritance hierarchies make logical sense.

**When to apply**: When designing inheritance hierarchies. Ask: "Can I use the subclass wherever I use the parent class without unexpected behavior?"

**Problem scenario**: You have a `Bird` class with a `fly()` method. You create a `Penguin` subclass, but penguins can't fly. Code that expects all birds to fly will break when given a penguin.

```kotlin
// Violates LSP - Penguin can't fly but inherits from Bird
open class Bird {
    open fun fly() {
        println("Flying through the sky")
    }
}

class Sparrow : Bird() {
    override fun fly() {
        println("Sparrow flying")
    }
}

class Penguin : Bird() {
    override fun fly() {
        throw Exception("Penguins can't fly!")  // Breaks the contract!
    }
}

// This function expects all birds to fly
fun makeBirdFly(bird: Bird) {
    bird.fly()  // Will crash if bird is a Penguin
}
```

**Solution**: Redesign the hierarchy to reflect actual capabilities.

```kotlin
// Follows LSP - Hierarchy reflects actual capabilities

abstract class Bird(val name: String)

// Only flying birds have the fly() method
interface Flyable {
    fun fly()
}

class Sparrow(name: String) : Bird(name), Flyable {
    override fun fly() {
        println("$name is flying")
    }
}

class Eagle(name: String) : Bird(name), Flyable {
    override fun fly() {
        println("$name is soaring")
    }
}

class Penguin(name: String) : Bird(name) {
    // No fly() method - penguin doesn't claim to be flyable
    fun swim() {
        println("$name is swimming")
    }
}

// Function now works only with flyable birds
fun makeFlyableBirdFly(bird: Flyable) {
    bird.fly()  // Will only accept birds that can actually fly
}
```

#### I - Interface Segregation Principle

**Principle**: Clients should not be forced to depend on methods they don't use. Better to have many small, specific interfaces than one large, general-purpose interface.

**Why it matters**: Prevents classes from implementing methods they don't need. Reduces coupling and makes code more focused.

**When to apply**: When you have an interface with many methods and most implementing classes only use a subset. When clients depend on interfaces with methods they never call.

**Problem scenario**: You have a `Worker` interface for employees. Full-time employees work, eat, and sleep at the office. But robot workers only work - they don't eat or sleep. The robot is forced to implement empty methods.

```kotlin
// Violates ISP - Forces all workers to implement all methods

interface Worker {
    fun work()
    fun eat()
    fun sleep()
}

class Human : Worker {
    override fun work() { println("Working...") }
    override fun eat() { println("Eating lunch...") }
    override fun sleep() { println("Taking a nap...") }
}

class Robot : Worker {
    override fun work() { println("Processing...") }
    override fun eat() { /* Empty - robots don't eat */ }
    override fun sleep() { /* Empty - robots don't sleep */ }
}
```

**Solution**: Split into smaller, focused interfaces.

```kotlin
// Follows ISP - Segregated interfaces based on capabilities

interface Workable {
    fun work()
}

interface Eatable {
    fun eat()
}

interface Sleepable {
    fun sleep()
}

class Human : Workable, Eatable, Sleepable {
    override fun work() { println("Working...") }
    override fun eat() { println("Eating lunch...") }
    override fun sleep() { println("Taking a nap...") }
}

class Robot : Workable {
    override fun work() { println("Processing...") }
    // Only implements what it needs
}

// Functions depend only on what they need
fun assignWork(worker: Workable) {
    worker.work()
}

fun serveLunch(eater: Eatable) {
    eater.eat()
}
```

#### D - Dependency Inversion Principle

**Principle**: High-level modules should not depend on low-level modules. Both should depend on abstractions. Abstractions should not depend on details; details should depend on abstractions.

**Why it matters**: Makes code flexible and testable. You can swap implementations without changing the code that uses them. Essential for unit testing with mocks.

**When to apply**: Always. Depend on interfaces, not concrete classes. Use dependency injection rather than creating dependencies inside classes.

**Problem scenario**: Your `OrderService` directly creates a `MySQLOrderRepository`. Now you want to:
- Switch to PostgreSQL
- Test `OrderService` without a real database
- Add caching with Redis

You can't do any of these without modifying `OrderService`.

```kotlin
// Violates DIP - OrderService depends on concrete implementation

class MySQLOrderRepository {
    fun save(order: Order) {
        println("Saving order to MySQL...")
        // MySQL-specific code
    }
}

class OrderService {
    private val repository = MySQLOrderRepository()  // Tight coupling!

    fun placeOrder(order: Order) {
        // Business logic
        repository.save(order)  // Tied to MySQL
    }
}
```

**Solution**: Depend on abstractions. Inject dependencies.

```kotlin
// Follows DIP - Both depend on abstraction

// Abstraction
interface OrderRepository {
    fun save(order: Order)
}

// Low-level module - implementation detail
class MySQLOrderRepository : OrderRepository {
    override fun save(order: Order) {
        println("Saving order to MySQL...")
    }
}

class PostgreSQLOrderRepository : OrderRepository {
    override fun save(order: Order) {
        println("Saving order to PostgreSQL...")
    }
}

class InMemoryOrderRepository : OrderRepository {
    private val orders = mutableListOf<Order>()

    override fun save(order: Order) {
        orders.add(order)
        println("Saved order to memory for testing")
    }
}

// High-level module - depends on abstraction
class OrderService(
    private val repository: OrderRepository  // Injected dependency
) {
    fun placeOrder(order: Order) {
        // Business logic
        repository.save(order)  // Works with any implementation
    }
}

// Usage - choose implementation at runtime
val mySQLRepo = MySQLOrderRepository()
val service1 = OrderService(mySQLRepo)

val postgresRepo = PostgreSQLOrderRepository()
val service2 = OrderService(postgresRepo)

val testRepo = InMemoryOrderRepository()
val testService = OrderService(testRepo)  // Easy to test!
```

### Key OOP Concepts in Practice

#### Composition vs Inheritance
Favor composition over inheritance for flexibility.

```kotlin
// Inheritance (rigid)
class Car : Engine, Wheels, Transmission { }

// Composition (flexible)
class Car(
    private val engine: Engine,
    private val wheels: Wheels,
    private val transmission: Transmission
)
```

#### Immutability
Prefer immutable objects for thread safety and predictability.

```kotlin
// Mutable (avoid if possible)
class MutableUser(var name: String, var email: String)

// Immutable (preferred)
data class User(val name: String, val email: String)

// To "modify", create a new instance
val updated = user.copy(email = "new@example.com")
```

---

## 6. Concurrency and IO in Backend Systems

### Understanding Execution Models

Backend applications spend most of their time waiting - waiting for databases to return data, waiting for external APIs to respond, waiting for files to be read from disk. Understanding how to handle this waiting efficiently is crucial for building scalable systems.

#### Serial vs Concurrent Execution

**Serial Execution**: Tasks are performed one after another. Each task must complete before the next begins.

**Scenario**: You need to fetch user data from a database, then fetch their orders, then fetch their preferences.

**Serial approach:**
```
Task 1: Fetch user data       [====] (100ms)
Task 2: Fetch orders                [====] (150ms)
Task 3: Fetch preferences                [====] (120ms)
Total time: 370ms
```

Each task waits for the previous one to complete, even though these operations are independent.

**Concurrent Execution**: Multiple tasks can be initiated and progress simultaneously.

**Concurrent approach:**
```
Task 1: Fetch user data       [====] (100ms)
Task 2: Fetch orders           [====] (150ms)
Task 3: Fetch preferences      [====] (120ms)
Total time: 150ms (the slowest task)
```

All three operations start together. The total time is determined by the slowest operation, not the sum of all operations.

**When to use each:**
- **Serial**: When tasks depend on each other (need user ID before fetching their orders)
- **Concurrent**: When tasks are independent (fetching user data and fetching product catalog simultaneously)

---

### Synchronous vs Asynchronous Processing

**Synchronous Processing**: The caller waits for the operation to complete before proceeding.

Think of calling a function like placing an order at a restaurant counter and standing there until your food is ready. You can't do anything else while waiting.

```kotlin
// Synchronous - execution stops here until database returns
fun getUser(id: String): User {
    val user = database.findById(id)  // Thread waits here
    return user
}
// Next line only executes after database returns
```

**Characteristics:**
- Easy to understand - code executes top to bottom
- Natural error handling with try-catch
- Thread is blocked during the operation
- Limited scalability - one thread per request

**Asynchronous Processing**: The caller initiates the operation and continues doing other work. When the operation completes, a callback or notification happens.

Think of placing an order and getting a buzzer. You're free to sit down, check your phone, or chat with friends. The buzzer alerts you when your food is ready.

#### Callbacks: The Foundation of Async

The simplest form of asynchronous processing uses **callbacks** - functions you provide that get called when the operation completes.

**JavaScript example (for intuition):**
```javascript
// Start the database query
database.findById(userId, function(user) {
    // This callback runs LATER when the database returns
    console.log("Got user:", user)
    // Do something with the user
})

// This line executes IMMEDIATELY, doesn't wait for database
console.log("Request initiated")

// Output:
// "Request initiated"  (prints first!)
// "Got user: ..."      (prints later, when DB returns)
```

The function doesn't return the user directly. Instead, you pass a callback function that will be invoked later with the result.

**The problem with callbacks:** Complex operations lead to "callback hell"
```javascript
database.findById(userId, function(user) {
    orderService.findOrders(user.id, function(orders) {
        paymentService.findPayments(user.id, function(payments) {
            // Deeply nested callbacks become hard to read
            // This is called "callback hell" or "pyramid of doom"
        })
    })
})
```

#### Promises and Reactive Types: A Better Way

To avoid callback hell, modern systems use **promises** or **reactive types** that represent a value that will arrive in the future.

In JavaScript, we have `Promise`. In our Kotlin/Spring stack, we use **`Mono`** (for single values) and **`Flux`** (for streams).

**Mono - A promise of one value:**
```kotlin
// Returns immediately with a Mono (a promise of a future User)
fun getUser(id: String): Mono<User> {
    return database.findById(id)  // Returns Mono<User> immediately
}
// Next line executes immediately, doesn't wait for database
```

**Why Mono is better than callbacks:**
```kotlin
// Instead of nested callbacks (callback hell):
// getUser -> then getOrders -> then getPayments

// Mono allows clean chaining:
database.findById(userId)           // Returns Mono<User>
    .flatMap { user ->                // When user arrives, get orders
        orderService.findOrders(user.id)
            .map { orders ->           // When orders arrive, combine
                UserProfile(user, orders)
            }
    }
// Flat, readable, avoids nesting!
```

**Characteristics:**
- More complex to understand than synchronous code
- Avoids callback hell through chaining
- Thread is free during the operation (can handle other requests)
- Highly scalable - one thread can handle thousands of concurrent operations

**Key insight**: Asynchronous doesn't necessarily mean concurrent. Asynchronous is about *when* you get the result (later, via callback or promise). Concurrent is about *how many* operations can progress simultaneously.

---

### Understanding Input/Output (IO)

Every backend application interacts with the outside world through IO operations:

**Types of IO:**

1. **Network IO**: Communicating over network
   - Database queries (PostgreSQL, MongoDB)
   - HTTP API calls (REST, GraphQL)
   - Message queues (Kafka, RabbitMQ)
   - Cache servers (Redis, Memcached)

2. **Disk IO**: Reading/writing to storage
   - File uploads/downloads
   - Log file writes
   - Configuration file reads
   - Image processing

3. **Inter-Process Communication (IPC)**
   - Talking to other services on same machine
   - Shared memory access
   - Unix sockets

**Characteristics of IO operations:**
- **Slow**: Compared to CPU operations, IO is extremely slow
  - CPU operation: nanoseconds (billionths of a second)
  - Memory access: ~100 nanoseconds
  - SSD read: ~100 microseconds (1000x slower than memory)
  - Network call within datacenter: ~500 microseconds
  - Database query: 1-100 milliseconds (millions of nanoseconds)
  - External API call: 50-500 milliseconds

- **Unpredictable**: Network latency varies, disks can be busy, services can be slow

**Impact on applications:**
A typical web request might:
- Query database: 10ms
- Call external API: 100ms
- Write logs: 5ms
- **Total IO time**: 115ms
- **Actual CPU computation time**: <1ms

Your application spends 99% of its time waiting for IO!

---

### Blocking vs Non-Blocking IO

This is the most critical concept for understanding reactive systems.

#### Blocking IO

When your code makes an IO request, the thread stops (blocks) until the operation completes.

**Analogy**: You go to a bank with only one teller. Each customer stands at the counter until their entire transaction is complete. Everyone else waits in line, even if the teller is just waiting for a manager's approval.

**What happens inside:**
```
Thread-1 handling Request A:
1. Receives request
2. Calls database.findUser()
3. Thread stops and waits... [=========] (10ms blocked)
4. Database returns result
5. Thread processes result
6. Sends response

While Thread-1 is blocked:
- Cannot handle other requests
- Consumes memory (thread stack)
- Context switching overhead when it wakes up
```

**Example scenario:**
```kotlin
// Blocking approach
fun handleRequest(request: UserRequest): User {
    val user = database.findById(request.userId)     // Thread blocks for 10ms
    val orders = orderService.findByUser(user.id)     // Thread blocks for 15ms
    val preferences = prefService.find(user.id)       // Thread blocks for 8ms
    return user
}
// Total time: 33ms, thread blocked entire time
```

**With 1000 concurrent requests:**
- Need ~1000 threads (assuming requests take ~1 second each)
- Each thread: ~1MB stack memory
- **Total memory**: ~1GB just for thread stacks
- CPU spends time context switching between threads
- Limited by thread pool size

**Limitation**: On a typical server, you can have a few thousand threads maximum. This limits your application to handling a few thousand concurrent requests.

#### Non-Blocking IO

When your code makes an IO request, the thread registers interest in the result and immediately moves on to do other work. When the IO completes, the thread is notified.

**Analogy**: You go to a modern coffee shop. You place your order, get a number, and step aside. The barista serves other customers while your coffee is being made. When your order is ready, your number is called. One barista efficiently handles many orders in progress.

##### OS-Level Foundation: Where Non-Blocking IO Actually Comes From

**Critical understanding**: Non-blocking IO is **not something your application creates** - it's a capability provided by the operating system. Without OS support, no amount of clever programming can make IO truly non-blocking.

**The OS primitives that enable non-blocking IO:**

```
┌─────────────────────────────────────────────────────┐
│          Operating System IO Mechanisms              │
├─────────────────────────────────────────────────────┤
│  Linux:   epoll   (edge-triggered, scalable)        │
│  macOS:   kqueue  (kernel event notification)       │
│  Windows: IOCP    (I/O Completion Ports)            │
└─────────────────────────────────────────────────────┘
                        ▲
                        │
                        │ Application frameworks
                        │ built on top of these
                        │
        ┌───────────────┴───────────────┐
        │                               │
    ┌───────┐                      ┌────────┐
    │ Netty │ (JVM)                │ libuv  │ (Node.js)
    └───────┘                      └────────┘
```

**How it works at the OS level:**

Traditional blocking IO:
```
Application: "Read from this socket"
OS: "Wait here..." [thread sleeps]
... network data arrives ...
OS: [wakes thread] "Here's your data"
```

Non-blocking IO with epoll (Linux):
```
Application: "Watch these 10,000 sockets, notify me when any have data"
OS: "Registered. Your thread is free to go."
Application: [thread does other work]
... network data arrives on socket #4523 ...
OS: [adds event to epoll queue] "Socket #4523 has data ready"
Application: [thread checks epoll] "Got it, reading from #4523"
```

**Why application-level code alone cannot achieve this:**

1. **Kernel-level monitoring**: The OS kernel monitors hardware interrupts from network cards, disk controllers, etc. Applications cannot access hardware directly.

2. **Efficient waiting**: `epoll/kqueue/IOCP` use kernel data structures to efficiently track thousands of file descriptors (sockets, files) without consuming thread resources.

3. **Event notification**: Only the OS kernel can receive hardware interrupts and convert them into application events.

**What application frameworks actually do:**

Application frameworks like **Netty** (used by Spring WebFlux) and **libuv** (used by Node.js) are **thin wrappers** around these OS primitives:

```kotlin
// What Netty does internally (simplified)
class EpollEventLoop {
    fun run() {
        while (true) {
            // This is a system call to the Linux kernel
            val events = epoll_wait(epollFd, maxEvents, timeout)

            for (event in events) {
                // Dispatch to application callback
                val channel = event.channel
                channel.handleRead()  // Your application code runs here
            }
        }
    }
}
```

**Cross-platform abstraction:**

Netty automatically selects the right OS primitive:
- On Linux → uses `epoll`
- On macOS → uses `kqueue`
- On Windows → uses IOCP

This is why the same Spring WebFlux code works on different operating systems - Netty handles the OS-specific details.

**Bottom line**: When you write non-blocking code using Reactor/Mono/Flux, you're ultimately relying on the operating system's ability to monitor IO operations efficiently. The reactive frameworks simply provide a developer-friendly API on top of these powerful OS capabilities.

**What happens inside:**
```
Thread-1 handling multiple requests concurrently:
1. Receives Request A
2. Initiates database.findUser() - registers callback
3. Immediately moves on (doesn't wait!)
4. Receives Request B
5. Initiates database.findOrder() - registers callback
6. Receives Request C
7. Initiates api.callExternal() - registers callback
... (handles hundreds of requests)
8. Database returns result for Request A - thread processes it
9. Database returns result for Request B - thread processes it
10. API returns result for Request C - thread processes it
```

**Example scenario:**
```kotlin
// Non-blocking approach
fun handleRequest(request: UserRequest): Mono<User> {
    return database.findById(request.userId)          // Returns immediately
        .flatMap { user ->
            // When user arrives, start next operation
            orderService.findByUser(user.id)
                .thenReturn(user)
        }
}
// Thread is free immediately, no blocking
```

**With 1000 concurrent requests:**
- Need ~10-20 threads (sized to CPU cores)
- Each request: minimal memory (just the callback chain)
- **Total memory**: ~100MB
- CPU efficiently processes work when IO completes
- Can handle 100,000+ concurrent requests on same hardware

**Advantage**: One thread can juggle thousands of concurrent operations by quickly switching between them as IO completes.

---

### The Reactor Pattern: Node.js as an Example

Before diving into the event loop, let's look at a concrete example: **Node.js**, which popularized the reactor pattern for building scalable servers.

#### Node.js Execution Model

Node.js demonstrates the reactor pattern perfectly. Here's how it works:

```
┌─────────────────────────────────────────────────────────────┐
│                    Node.js Architecture                      │
└─────────────────────────────────────────────────────────────┘

    JavaScript Code (Single-threaded)
            │
            ▼
    ┌───────────────────┐
    │   Event Loop      │ ◄──┐
    │  (Single Thread)  │    │
    └─────────┬─────────┘    │
              │              │
              │ Delegates    │ Notifies
              │ IO work      │ on completion
              ▼              │
    ┌───────────────────┐    │
    │   libuv           │    │
    │ (Thread Pool for  │────┘
    │  blocking IO)     │
    └─────────┬─────────┘
              │
              │ Uses OS primitives
              ▼
    ┌───────────────────┐
    │  Operating System │
    │  - epoll (Linux)  │
    │  - kqueue (macOS) │
    │  - IOCP (Windows) │
    └───────────────────┘
```

**How a request flows through Node.js:**

```
1. Request arrives
   ↓
2. Event Loop picks it up
   ↓
3. JavaScript code executes (synchronous part)
   ↓
4. Encounters IO operation (e.g., database query)
   ↓
5. Delegates to libuv with callback
   ↓
6. Event Loop continues (doesn't wait!)
   ↓
7. Handles other requests...
   ↓
8. libuv completes IO operation
   ↓
9. Adds callback to Event Loop queue
   ↓
10. Event Loop executes callback
    ↓
11. Response sent to client
```

#### Concrete Example: HTTP Server in Node.js

```javascript
// Node.js server handling multiple requests concurrently
const http = require('http');
const db = require('./database');

const server = http.createServer((req, res) => {
    console.log('Request received');  // Runs on Event Loop thread

    // This is non-blocking! Event Loop doesn't wait
    db.query('SELECT * FROM users', (err, users) => {
        // This callback runs later when DB returns
        console.log('DB returned');
        res.writeHead(200, {'Content-Type': 'application/json'});
        res.end(JSON.stringify(users));
    });

    console.log('Request handler finished'); // Runs immediately!
    // Event Loop is now free to handle other requests
});

server.listen(3000);
```

**Output order:**
```
Request received
Request handler finished  ← Event Loop is free!
... (Event Loop handles other requests)
DB returned              ← When database responds
```

#### Why This Matters

**Comparison with traditional threading:**

```
Traditional (Blocking):
Thread 1: [Request A ████████waiting████████ Response]
Thread 2: [Request B ████████waiting████████ Response]
Thread 3: [Request C ████████waiting████████ Response]
Need: 1000 threads for 1000 concurrent requests

Node.js (Reactor Pattern):
Event Loop: [A-start][B-start][C-start]...[A-callback][B-callback][C-callback]
Need: 1 main thread + small thread pool for 1000s of concurrent requests
```

#### Our Stack: Spring WebFlux + Reactor

Our tech stack uses the same reactor pattern, but implemented in the JVM:

```
┌─────────────────────────────────────────────────────────────┐
│              Spring WebFlux Architecture                     │
└─────────────────────────────────────────────────────────────┘

    Kotlin/Java Code
            │
            ▼
    ┌───────────────────┐
    │    Reactor        │
    │ (Mono, Flux API)  │
    └─────────┬─────────┘
              │
              ▼
    ┌───────────────────┐
    │     Netty         │ ◄──┐
    │  (Event Loops)    │    │
    └─────────┬─────────┘    │
              │              │
              │ Delegates    │ Notifies
              ▼              │
    ┌───────────────────┐    │
    │  Operating System │────┘
    │  - epoll (Linux)  │
    │  - kqueue (macOS) │
    └───────────────────┘
```

**Same pattern, different implementation:**
- Node.js uses JavaScript + libuv
- Spring WebFlux uses Kotlin/Java + Netty
- Both use the reactor pattern with event loops
- Both achieve high concurrency with minimal threads

---

### The Event Loop: The Engine of Non-Blocking IO

The event loop is the mechanism that makes non-blocking IO work. Think of it as a highly efficient task coordinator.

#### Conceptual Model

**The Loop:**
```
while (true) {
    1. Check if any IO operations have completed
    2. For each completed operation:
       - Execute its callback
       - If callback triggers more IO, register it
    3. Check if any new requests arrived
    4. For each new request:
       - Start processing
       - If it needs IO, register it and move on
    5. Repeat
}
```

**Key characteristics:**
- **Single-threaded** (in the simplest form): One thread runs the loop
- **Non-blocking**: Never waits for IO
- **Event-driven**: Reacts to events (IO completion, new request arrival)

#### How It Works: A Detailed Example

**Scenario**: Three requests arrive nearly simultaneously.

```
Timeline of Event Loop:

t=0ms: Request 1 arrives (fetch user ID=1)
Event Loop:
  - Receives request 1
  - Initiates database.findById(1)
  - Registers callback: "When DB returns, send user data to client"
  - Loop continues (doesn't wait!)

t=1ms: Request 2 arrives (fetch user ID=2)
Event Loop:
  - Receives request 2
  - Initiates database.findById(2)
  - Registers callback for request 2
  - Loop continues

t=2ms: Request 3 arrives (fetch user ID=3)
Event Loop:
  - Receives request 3
  - Initiates database.findById(3)
  - Registers callback for request 3
  - Loop continues

t=3-10ms: Loop keeps checking for completed IO
  - No IO completed yet
  - Thread is free, could handle more requests
  - Or perform other work

t=10ms: Database returns result for Request 1
Event Loop:
  - Detects IO completion
  - Executes callback: send user data to client
  - Request 1 complete!

t=11ms: Database returns result for Request 3
Event Loop:
  - Detects IO completion
  - Executes callback: send user data to client
  - Request 3 complete!

t=15ms: Database returns result for Request 2
Event Loop:
  - Detects IO completion
  - Executes callback: send user data to client
  - Request 2 complete!
```

**What just happened:**
- **One thread** handled three concurrent requests
- Thread was never blocked
- Requests were handled concurrently (overlapping in time)
- Total wall-clock time: 15ms
- If these were handled serially with blocking IO: 30ms

#### Under the Hood: Operating System Support

The event loop relies on OS-level mechanisms to know when IO is ready:

**On Linux: epoll**
```
1. Application: "Hey OS, I want to read from these 1000 network sockets"
2. OS: "Okay, I'll monitor them"
3. Application: "Tell me when any of them have data"
4. OS: "Will do"
(Application thread is free to do other work)
5. OS (later): "Socket #47 and #234 have data ready!"
6. Application: "Thanks!" (processes those two sockets)
```

**On MacOS: kqueue**
**On Windows: IOCP (IO Completion Ports)**

Same concept, different implementations. The OS efficiently monitors thousands of IO sources and notifies your application when something is ready.

#### Event Loop in Our Tech Stack

**Netty (used by Spring WebFlux):**
- Implements the event loop pattern
- Manages multiple event loops (one per CPU core typically)
- Each event loop handles thousands of connections
- Uses OS-specific mechanisms (epoll on Linux)

**Reactor (our reactive library):**
- Built on top of event loops
- Provides high-level abstractions (`Mono`, `Flux`)
- Automatically schedules work on appropriate event loops
- Handles callback chains efficiently

**Your application code:**
```kotlin
// This looks simple, but under the hood:
fun getUser(id: String): Mono<User> {
    return database.findById(id)
        .flatMap { user ->
            orderService.fetchOrders(user.id)
                .map { orders ->
                    user.copy(orderCount = orders.size)
                }
        }
}

// What actually happens:
// 1. Request arrives on Event Loop thread
// 2. database.findById() initiates async DB query
// 3. Event Loop registers callback, continues to other work
// 4. When DB responds, Event Loop executes first callback
// 5. orderService.fetchOrders() initiates another async call
// 6. Event Loop registers another callback
// 7. When orders arrive, final callback executes
// 8. Result sent to client
// All on Event Loop threads, no blocking!
```

---

### Bringing It All Together: Why This Matters

#### Traditional Blocking Model (Thread-per-Request)

**Architecture:**
```
Request → Thread Pool → Thread → Blocking IO → Response
```

**Characteristics:**
- **Simple to understand**: Linear, top-to-bottom code flow
- **Limited scalability**: Constrained by thread pool size
- **Memory intensive**: Each thread ~1MB stack
- **Inefficient**: Threads mostly waiting, not working

**Suitable for:**
- CPU-intensive applications (video encoding, data processing)
- Simple applications with low concurrency
- Legacy systems

#### Reactive Non-Blocking Model (Event Loop)

**Architecture:**
```
Request → Event Loop → Register IO → Callback → Response
          ↑            ↓
          ← IO Complete ←
```

**Characteristics:**
- **Complex to understand**: Non-linear, callback-based flow
- **Highly scalable**: Handle 100k+ concurrent requests
- **Memory efficient**: Minimal overhead per request
- **Efficient**: Threads always doing useful work

**Suitable for:**
- IO-intensive applications (web services, APIs)
- High-concurrency requirements
- Microservices architecture
- Modern cloud-native applications

#### Real-World Impact

**Scenario**: API that makes 3 external calls per request

**Blocking approach:**
```
Thread pool: 200 threads
Each request: 300ms (3 × 100ms IO calls)
Throughput: 200 / 0.3 = 666 requests/second
Memory: 200 threads × 1MB = 200MB (just threads)
```

**Non-blocking approach:**
```
Event loop threads: 8 (one per CPU core)
Each request: 100ms (concurrent IO calls)
Throughput: Limited by IO, not threads → 5000+ requests/second
Memory: 8 threads + request overhead = 50MB
```

**Result**: ~7.5x higher throughput, 75% less memory, on the same hardware!

---

### Practical Implications for Your Code

#### What You Should Do:

1. **Never block in reactive code:**
   ```kotlin
   // BAD - blocks event loop thread!
   fun getUser(id: String): Mono<User> {
       Thread.sleep(1000)  // NEVER DO THIS
       return Mono.just(user)
   }

   // GOOD - stays non-blocking
   fun getUser(id: String): Mono<User> {
       return userRepository.findById(id)
   }
   ```

2. **Use reactive database drivers:**
   ```kotlin
   // BAD - JDBC is blocking
   val user = jdbcTemplate.queryForObject(sql, UserRowMapper())

   // GOOD - R2DBC is non-blocking
   val user: Mono<User> = r2dbcTemplate.selectOne(query, User::class.java)
   ```

3. **Chain operations reactively:**
   ```kotlin
   // Combines multiple async operations efficiently
   fun getUserProfile(userId: String): Mono<UserProfile> {
       return userService.findById(userId)
           .flatMap { user ->
               orderService.findByUser(user.id)
                   .collectList()
                   .map { orders ->
                       UserProfile(user, orders)
                   }
           }
   }
   ```

4. **Understand execution context:**
   - Your code runs on event loop threads
   - These threads must never block
   - If you must do blocking work, offload it to a separate thread pool

---

### Key Takeaways

1. **Serial vs Concurrent**: Serial means one after another; concurrent means overlapping in time.

2. **Sync vs Async**: Synchronous blocks the caller; asynchronous returns immediately with a promise.

3. **IO is slow**: Applications spend most time waiting for IO, not computing.

4. **Blocking vs Non-blocking**: Blocking ties up a thread while waiting; non-blocking frees the thread to do other work.

5. **Event Loop**: The coordination mechanism that enables one thread to handle thousands of concurrent operations by reacting to IO completion events.

6. **Reactive programming**: A programming model built on non-blocking IO and event loops, enabling highly scalable applications.

**Mental model**: Think of your application as a restaurant. Blocking IO is one waiter who stands at each table until the kitchen finishes that order. Non-blocking IO is a waiter who takes orders from many tables, and delivers food when the kitchen signals it's ready. Same number of waiters, many more satisfied customers!

---

## 7. SQL and Databases

### What is SQL?

SQL (Structured Query Language) is a standard language for managing relational databases.

### Database Concepts

#### Tables
Structured data storage with rows and columns.

```
users table:
┌────┬───────────┬──────────────────┬─────────────┐
│ id │   name    │      email       │  created_at │
├────┼───────────┼──────────────────┼─────────────┤
│  1 │ Alice     │ alice@example.com│ 2025-01-01  │
│  2 │ Bob       │ bob@example.com  │ 2025-01-02  │
└────┴───────────┴──────────────────┴─────────────┘
```

#### Primary Key
Unique identifier for each row.

#### Foreign Key
References primary key in another table, establishing relationships.

### Essential SQL Commands

#### Data Definition Language (DDL)

```sql
-- Create table
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    age INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Modify table
ALTER TABLE users ADD COLUMN phone VARCHAR(15);
ALTER TABLE users DROP COLUMN age;

-- Delete table
DROP TABLE users;
```

#### Data Manipulation Language (DML)

```sql
-- Insert data
INSERT INTO users (name, email)
VALUES ('Alice', 'alice@example.com');

INSERT INTO users (name, email)
VALUES
    ('Bob', 'bob@example.com'),
    ('Carol', 'carol@example.com');

-- Select data
SELECT * FROM users;
SELECT name, email FROM users WHERE age > 18;
SELECT * FROM users ORDER BY created_at DESC;
SELECT * FROM users LIMIT 10;

-- Update data
UPDATE users
SET email = 'newemail@example.com'
WHERE id = 1;

-- Delete data
DELETE FROM users WHERE id = 1;
```

#### Data Query Language (DQL)

```sql
-- Filtering
SELECT * FROM users WHERE name = 'Alice';
SELECT * FROM users WHERE age >= 18 AND age <= 65;
SELECT * FROM users WHERE email LIKE '%@example.com';

-- Sorting
SELECT * FROM users ORDER BY name ASC;
SELECT * FROM users ORDER BY created_at DESC;

-- Aggregation
SELECT COUNT(*) FROM users;
SELECT AVG(age) FROM users;
SELECT MAX(age), MIN(age) FROM users;
SELECT status, COUNT(*) FROM users GROUP BY status;

-- Grouping with filter
SELECT status, COUNT(*)
FROM users
GROUP BY status
HAVING COUNT(*) > 5;
```

### Joins

Combine data from multiple tables.

```sql
-- Sample tables
CREATE TABLE departments (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE employees (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    department_id INTEGER REFERENCES departments(id)
);

-- INNER JOIN: Only matching rows
SELECT employees.name, departments.name
FROM employees
INNER JOIN departments ON employees.department_id = departments.id;

-- LEFT JOIN: All rows from left table, matching from right
SELECT employees.name, departments.name
FROM employees
LEFT JOIN departments ON employees.department_id = departments.id;

-- RIGHT JOIN: All rows from right table, matching from left
SELECT employees.name, departments.name
FROM employees
RIGHT JOIN departments ON employees.department_id = departments.id;

-- FULL OUTER JOIN: All rows from both tables
SELECT employees.name, departments.name
FROM employees
FULL OUTER JOIN departments ON employees.department_id = departments.id;
```

### Subqueries

Nested queries for complex operations.

```sql
-- Subquery in WHERE
SELECT name FROM users
WHERE id IN (SELECT user_id FROM orders WHERE amount > 1000);

-- Subquery in SELECT
SELECT
    name,
    (SELECT COUNT(*) FROM orders WHERE orders.user_id = users.id) as order_count
FROM users;
```

### Indexes

Speed up data retrieval.

```sql
-- Create index
CREATE INDEX idx_users_email ON users(email);

-- Unique index
CREATE UNIQUE INDEX idx_users_email ON users(email);

-- Composite index
CREATE INDEX idx_users_name_email ON users(name, email);

-- Drop index
DROP INDEX idx_users_email;
```

### Transactions

Group operations that must succeed or fail together.

```sql
BEGIN;

UPDATE accounts SET balance = balance - 100 WHERE id = 1;
UPDATE accounts SET balance = balance + 100 WHERE id = 2;

-- If everything OK
COMMIT;

-- If something went wrong
ROLLBACK;
```

### Practice Exercises

1. **Basic Queries**
   - Create a `products` table with columns: id, name, price, category
   - Insert 10 products
   - Find all products with price > 100
   - Find average price by category

2. **Joins**
   - Create `orders` table with: id, user_id, product_id, quantity
   - Join to find all orders with user names and product names
   - Find users who have never placed an order

3. **Aggregations**
   - Count total orders per user
   - Find top 5 users by total order amount
   - Calculate monthly revenue

---

## 8. PostgreSQL Features and Internals

### Why PostgreSQL?

- **ACID compliance**: Atomicity, Consistency, Isolation, Durability
- **Advanced features**: JSON, full-text search, arrays, custom types
- **Extensibility**: Custom functions, operators, data types
- **Performance**: Powerful query optimizer, parallel queries
- **Reliability**: Robust and battle-tested

### PostgreSQL-Specific Features

#### JSON Support

```sql
-- Create table with JSON
CREATE TABLE events (
    id SERIAL PRIMARY KEY,
    data JSONB
);

-- Insert JSON data
INSERT INTO events (data)
VALUES ('{"user_id": 123, "event": "login", "timestamp": "2025-01-01T10:00:00Z"}');

-- Query JSON fields
SELECT data->>'user_id' as user_id FROM events;
SELECT * FROM events WHERE data->>'event' = 'login';

-- JSON operators
SELECT data->'user' FROM events;           -- Returns JSON
SELECT data->>'user' FROM events;          -- Returns text
SELECT data->'user'->>'name' FROM events;  -- Nested access
SELECT data @> '{"event":"login"}';        -- Contains
```

#### Arrays

```sql
-- Array column
CREATE TABLE posts (
    id SERIAL PRIMARY KEY,
    tags TEXT[]
);

INSERT INTO posts (tags) VALUES (ARRAY['postgres', 'sql', 'database']);

-- Query arrays
SELECT * FROM posts WHERE 'postgres' = ANY(tags);
SELECT * FROM posts WHERE tags @> ARRAY['postgres'];
```

#### Full-Text Search

```sql
-- Create text search column
ALTER TABLE articles ADD COLUMN search_vector tsvector;

-- Update search vector
UPDATE articles
SET search_vector = to_tsvector('english', title || ' ' || content);

-- Search
SELECT * FROM articles
WHERE search_vector @@ to_tsquery('english', 'postgresql & database');

-- Create index for performance
CREATE INDEX idx_search ON articles USING GIN(search_vector);
```

#### Window Functions

Perform calculations across rows related to current row.

```sql
-- Rank products by price within each category
SELECT
    name,
    category,
    price,
    RANK() OVER (PARTITION BY category ORDER BY price DESC) as price_rank
FROM products;

-- Running total
SELECT
    date,
    amount,
    SUM(amount) OVER (ORDER BY date) as running_total
FROM transactions;

-- Moving average
SELECT
    date,
    value,
    AVG(value) OVER (ORDER BY date ROWS BETWEEN 6 PRECEDING AND CURRENT ROW) as moving_avg_7day
FROM metrics;
```

#### Common Table Expressions (CTEs)

Temporary named result sets.

```sql
-- Simple CTE
WITH high_value_customers AS (
    SELECT user_id, SUM(amount) as total
    FROM orders
    GROUP BY user_id
    HAVING SUM(amount) > 10000
)
SELECT users.name, high_value_customers.total
FROM users
JOIN high_value_customers ON users.id = high_value_customers.user_id;

-- Recursive CTE (hierarchical data)
WITH RECURSIVE employee_hierarchy AS (
    -- Base case
    SELECT id, name, manager_id, 1 as level
    FROM employees
    WHERE manager_id IS NULL

    UNION ALL

    -- Recursive case
    SELECT e.id, e.name, e.manager_id, eh.level + 1
    FROM employees e
    JOIN employee_hierarchy eh ON e.manager_id = eh.id
)
SELECT * FROM employee_hierarchy;
```

### PostgreSQL Internals (Overview)

#### Architecture

```
Client Application
        ↓
  PostgreSQL Server
        ↓
┌─────────────────────┐
│   Postmaster        │  (Main process)
│   ├─ Backend 1      │  (Per connection)
│   ├─ Backend 2      │
│   └─ Backend n      │
└─────────────────────┘
        ↓
┌─────────────────────┐
│  Shared Memory      │
│  - Shared Buffers   │  (Cache)
│  - WAL Buffers      │  (Write-ahead log)
│  - Lock Tables      │
└─────────────────────┘
        ↓
┌─────────────────────┐
│  Storage            │
│  - Data Files       │
│  - WAL Files        │
│  - Config Files     │
└─────────────────────┘
```

#### MVCC (Multi-Version Concurrency Control)

PostgreSQL uses MVCC to handle concurrent transactions without locking.

**Key Concept**: Each transaction sees a snapshot of the database as it existed at transaction start.

```sql
-- Transaction 1
BEGIN;
SELECT balance FROM accounts WHERE id = 1;  -- Sees $100
-- Transaction 1 waits...

-- Transaction 2 (meanwhile)
BEGIN;
UPDATE accounts SET balance = 200 WHERE id = 1;
COMMIT;

-- Transaction 1 continues
SELECT balance FROM accounts WHERE id = 1;  -- Still sees $100 (snapshot isolation)
COMMIT;

-- New transaction
SELECT balance FROM accounts WHERE id = 1;  -- Now sees $200
```

**Implications:**
- Readers don't block writers
- Writers don't block readers
- Multiple versions of rows exist
- VACUUM needed to clean up old versions

#### Write-Ahead Logging (WAL)

Changes are first written to WAL before modifying data files.

**Benefits:**
- Crash recovery: Replay WAL to recover
- Replication: Ship WAL to replicas
- Point-in-time recovery

#### Query Execution

```sql
-- View query plan
EXPLAIN SELECT * FROM users WHERE email = 'alice@example.com';

-- View actual execution
EXPLAIN ANALYZE SELECT * FROM users WHERE email = 'alice@example.com';
```

**Execution Pipeline:**
1. **Parser**: SQL → Parse tree
2. **Rewriter**: Apply rules (views, etc.)
3. **Planner**: Generate optimal execution plan
4. **Executor**: Execute plan and return results

#### Indexes

```sql
-- B-tree (default, most common)
CREATE INDEX idx_users_email ON users(email);

-- Hash (equality comparisons only)
CREATE INDEX idx_users_email ON users USING HASH(email);

-- GIN (full-text search, JSON, arrays)
CREATE INDEX idx_posts_tags ON posts USING GIN(tags);

-- GiST (geometric data, full-text)
CREATE INDEX idx_locations ON places USING GIST(location);

-- Check index usage
SELECT schemaname, tablename, indexname, idx_scan
FROM pg_stat_user_indexes
ORDER BY idx_scan;
```

#### Vacuum

Cleans up dead tuples from MVCC.

```sql
-- Manual vacuum
VACUUM users;

-- Analyze statistics (for query planner)
ANALYZE users;

-- Vacuum and analyze
VACUUM ANALYZE users;

-- Full vacuum (reclaims space, locks table)
VACUUM FULL users;
```

**Autovacuum**: Background process that automatically vacuums tables.

### Performance Tips

1. **Use Indexes Wisely**
   - Index columns used in WHERE, JOIN, ORDER BY
   - Don't over-index (slows writes)
   - Use `EXPLAIN ANALYZE` to verify

2. **Optimize Queries**
   - Select only needed columns
   - Use LIMIT for pagination
   - Avoid N+1 queries (use JOINs or batch fetching)

3. **Connection Pooling**
   - PostgreSQL creates a process per connection (expensive)
   - Use connection pooler like PgBouncer or HikariCP

4. **Proper Data Types**
   - Use appropriate types (INTEGER vs BIGINT)
   - TEXT vs VARCHAR (TEXT is fine in Postgres)
   - Use TIMESTAMP WITH TIME ZONE

5. **Monitoring**
   ```sql
   -- Active queries
   SELECT pid, usename, state, query
   FROM pg_stat_activity
   WHERE state = 'active';

   -- Table sizes
   SELECT
       schemaname,
       tablename,
       pg_size_pretty(pg_total_relation_size(schemaname||'.'||tablename)) AS size
   FROM pg_tables
   ORDER BY pg_total_relation_size(schemaname||'.'||tablename) DESC;

   -- Cache hit ratio (should be > 99%)
   SELECT
       sum(heap_blks_read) as heap_read,
       sum(heap_blks_hit) as heap_hit,
       sum(heap_blks_hit) / (sum(heap_blks_hit) + sum(heap_blks_read)) as ratio
   FROM pg_statio_user_tables;
   ```

---

## 9. Gradle Build Tool

### What is Gradle?

Gradle is a build automation tool used for:
- Compiling code
- Managing dependencies
- Running tests
- Packaging applications
- Publishing artifacts

### Project Structure

```
project-root/
├── build.gradle.kts       # Build configuration (Kotlin DSL)
├── settings.gradle.kts    # Multi-project settings
├── gradle/
│   └── wrapper/           # Gradle wrapper files
├── gradlew                # Unix wrapper script
├── gradlew.bat            # Windows wrapper script
└── src/
    ├── main/
    │   ├── kotlin/        # Application code
    │   └── resources/     # Config files
    └── test/
        ├── kotlin/        # Test code
        └── resources/     # Test resources
```

### Basic Commands

```bash
# Build project (compile + test + package)
./gradlew build

# Compile only
./gradlew compileKotlin

# Run tests
./gradlew test

# Run specific test
./gradlew test --tests "com.example.MyTest"

# Clean build artifacts
./gradlew clean

# Run application
./gradlew bootRun  # For Spring Boot

# View dependencies
./gradlew dependencies

# List all tasks
./gradlew tasks

# Build without tests (faster)
./gradlew build -x test
```

### Build File Structure (build.gradle.kts)

```kotlin
// Plugin configuration
plugins {
    kotlin("jvm") version "1.9.0"
    id("org.springframework.boot") version "3.1.0"
    kotlin("plugin.spring") version "1.9.0"
}

// Project metadata
group = "com.example"
version = "1.0.0"

// Java version
java {
    sourceCompatibility = JavaVersion.VERSION_17
}

// Dependency repositories
repositories {
    mavenCentral()
}

// Dependencies
dependencies {
    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Database
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.postgresql:r2dbc-postgresql")

    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
}

// Test configuration
tasks.withType<Test> {
    useJUnitPlatform()
}

// Kotlin compilation options
tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}
```

### Dependency Management

#### Dependency Scopes
- `implementation`: Compile and runtime dependency
- `api`: Exposes dependency to consumers (for libraries)
- `runtimeOnly`: Only needed at runtime
- `compileOnly`: Only needed at compile time
- `testImplementation`: Only for tests

#### Version Catalogs

Modern way to manage versions:

```kotlin
// gradle/libs.versions.toml
[versions]
kotlin = "1.9.0"
spring-boot = "3.1.0"

[libraries]
spring-boot-webflux = { module = "org.springframework.boot:spring-boot-starter-webflux", version.ref = "spring-boot" }
kotlin-reflect = { module = "org.jetbrains.kotlin:kotlin-reflect", version.ref = "kotlin" }

// In build.gradle.kts
dependencies {
    implementation(libs.spring.boot.webflux)
    implementation(libs.kotlin.reflect)
}
```

### Multi-Project Builds

```kotlin
// settings.gradle.kts
rootProject.name = "my-app"
include("server", "client", "common")

// Build all projects
./gradlew build

// Build specific project
./gradlew :server:build
```

### Gradle Wrapper

Always use the wrapper (`./gradlew`) instead of globally installed Gradle.

**Benefits:**
- Ensures everyone uses same Gradle version
- No need to install Gradle
- Reproducible builds

```bash
# Update wrapper version
./gradlew wrapper --gradle-version 8.5
```

### Common Tasks

```bash
# Debug build issues
./gradlew build --stacktrace
./gradlew build --info
./gradlew build --debug

# Refresh dependencies (force re-download)
./gradlew build --refresh-dependencies

# Build continuously (recompile on changes)
./gradlew build --continuous

# Generate IDE project files
./gradlew idea      # IntelliJ IDEA
./gradlew eclipse   # Eclipse
```

### Performance Tips

```bash
# Enable Gradle daemon (usually on by default)
./gradlew build --daemon

# Parallel execution
./gradlew build --parallel

# Build cache
./gradlew build --build-cache
```

**gradle.properties:**
```properties
org.gradle.caching=true
org.gradle.parallel=true
org.gradle.jvmargs=-Xmx2g -XX:MaxMetaspaceSize=512m
```

---

## 10. Spring WebFlux

### What is Spring WebFlux?

Spring WebFlux is Spring's reactive web framework for building non-blocking, asynchronous applications.

**Key Characteristics:**
- **Non-blocking**: Doesn't wait for I/O operations
- **Reactive**: Data flows as streams (Reactive Streams API)
- **Scalable**: Handles many concurrent connections with fewer threads
- **Functional**: Supports both annotated controllers and functional routing

### Reactive Programming Concepts

#### Traditional (Blocking) Model
```kotlin
// Each request uses a thread that waits
fun getUser(id: String): User {
    val user = database.findById(id)  // Thread waits here
    return user
}
// Limited by thread pool size
```

#### Reactive (Non-Blocking) Model
```kotlin
// Thread is released while waiting
fun getUser(id: String): Mono<User> {
    return database.findById(id)  // Returns immediately with Mono
}
// Can handle many more concurrent requests
```

### Core Reactive Types

#### Mono<T>
Represents 0 or 1 item.

```kotlin
// Create Mono
val mono = Mono.just("Hello")
val empty = Mono.empty<String>()
val error = Mono.error<String>(RuntimeException("Error"))

// Operations
mono
    .map { it.uppercase() }                     // Transform
    .filter { it.startsWith("H") }              // Filter
    .flatMap { callAnotherService(it) }         // Chain async calls
    .defaultIfEmpty("Default")                  // Fallback
    .doOnNext { println("Got: $it") }           // Side effect
    .subscribe { result -> println(result) }    // Subscribe (triggers execution)

// Example: HTTP request
fun getUserById(id: String): Mono<User> {
    return webClient
        .get()
        .uri("/users/$id")
        .retrieve()
        .bodyToMono(User::class.java)
}
```

#### Flux<T>
Represents 0 to N items (stream).

```kotlin
// Create Flux
val flux = Flux.just("A", "B", "C")
val range = Flux.range(1, 10)
val empty = Flux.empty<String>()

// Operations
flux
    .map { it.lowercase() }                     // Transform each
    .filter { it != "b" }                       // Filter
    .flatMap { callAnotherService(it) }         // Chain async calls
    .take(5)                                    // Limit
    .collectList()                              // Convert to Mono<List>
    .subscribe { result -> println(result) }

// Example: Stream from database
fun getAllUsers(): Flux<User> {
    return userRepository.findAll()  // Returns Flux<User>
}
```

### Spring WebFlux Components

#### Controller

```kotlin
@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService
) {
    @GetMapping("/{id}")
    fun getUser(@PathVariable id: String): Mono<User> {
        return userService.findById(id)
    }

    @GetMapping
    fun getAllUsers(): Flux<User> {
        return userService.findAll()
    }

    @PostMapping
    fun createUser(@RequestBody user: User): Mono<User> {
        return userService.create(user)
    }

    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id: String,
        @RequestBody user: User
    ): Mono<User> {
        return userService.update(id, user)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: String): Mono<Void> {
        return userService.delete(id)
    }
}
```

#### Service Layer

```kotlin
@Service
class UserService(
    private val userRepository: UserRepository,
    private val notificationService: NotificationService
) {
    fun findById(id: String): Mono<User> {
        return userRepository.findById(id)
            .switchIfEmpty(Mono.error(UserNotFoundException(id)))
    }

    fun create(user: User): Mono<User> {
        return userRepository.save(user)
            .flatMap { saved ->
                // Chain operations
                notificationService.sendWelcomeEmail(saved)
                    .thenReturn(saved)
            }
    }

    fun findAll(): Flux<User> {
        return userRepository.findAll()
    }
}
```

#### Repository (R2DBC)

```kotlin
interface UserRepository : R2dbcRepository<User, String> {
    fun findByEmail(email: String): Mono<User>

    @Query("SELECT * FROM users WHERE age > :age")
    fun findByAgeGreaterThan(age: Int): Flux<User>
}
```

#### Data Class (Entity)

```kotlin
@Table("users")
data class User(
    @Id
    val id: String? = null,
    val name: String,
    val email: String,
    val age: Int,
    val createdAt: LocalDateTime = LocalDateTime.now()
)
```

### Error Handling

```kotlin
fun getUser(id: String): Mono<User> {
    return userRepository.findById(id)
        .switchIfEmpty(Mono.error(UserNotFoundException(id)))
        .onErrorResume { error ->
            when (error) {
                is UserNotFoundException -> Mono.error(ResponseStatusException(HttpStatus.NOT_FOUND))
                else -> Mono.error(ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR))
            }
        }
}

// Global error handler
@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFound(ex: UserNotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse(ex.message ?: "User not found"))
    }
}
```

### WebClient (HTTP Client)

```kotlin
@Configuration
class WebClientConfig {
    @Bean
    fun webClient(): WebClient {
        return WebClient.builder()
            .baseUrl("https://api.example.com")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build()
    }
}

@Service
class ExternalApiService(private val webClient: WebClient) {
    fun fetchData(id: String): Mono<Data> {
        return webClient
            .get()
            .uri("/data/{id}", id)
            .retrieve()
            .onStatus({ it.isError }, { response ->
                response.bodyToMono(String::class.java)
                    .flatMap { Mono.error(RuntimeException(it)) }
            })
            .bodyToMono(Data::class.java)
            .timeout(Duration.ofSeconds(5))
            .retry(3)
    }
}
```

### Combining Publishers

```kotlin
// Combine multiple Monos
fun getUserProfile(userId: String): Mono<UserProfile> {
    val userMono = userService.findById(userId)
    val ordersMono = orderService.getOrdersByUser(userId).collectList()
    val preferencesMono = preferencesService.findByUser(userId)

    return Mono.zip(userMono, ordersMono, preferencesMono)
        .map { tuple ->
            UserProfile(
                user = tuple.t1,
                orders = tuple.t2,
                preferences = tuple.t3
            )
        }
}

// Merge multiple Fluxes
fun getAllNotifications(): Flux<Notification> {
    val emailNotifications = emailService.getNotifications()
    val smsNotifications = smsService.getNotifications()
    val pushNotifications = pushService.getNotifications()

    return Flux.merge(emailNotifications, smsNotifications, pushNotifications)
        .sort(compareBy { it.timestamp })
}

// Process sequentially
fun processOrder(order: Order): Mono<Receipt> {
    return validateOrder(order)
        .flatMap { processPayment(it) }
        .flatMap { updateInventory(it) }
        .flatMap { generateReceipt(it) }
}
```

### Testing

```kotlin
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {
    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    fun `should get user by id`() {
        webTestClient.get()
            .uri("/api/users/123")
            .exchange()
            .expectStatus().isOk
            .expectBody<User>()
            .consumeWith { response ->
                assertThat(response.responseBody?.id).isEqualTo("123")
            }
    }

    @Test
    fun `should return 404 for non-existent user`() {
        webTestClient.get()
            .uri("/api/users/999")
            .exchange()
            .expectStatus().isNotFound
    }
}

// Unit test with StepVerifier
@Test
fun `should find user by id`() {
    val user = User(id = "123", name = "Alice", email = "alice@example.com", age = 30)

    `when`(userRepository.findById("123")).thenReturn(Mono.just(user))

    StepVerifier.create(userService.findById("123"))
        .expectNext(user)
        .verifyComplete()
}

@Test
fun `should handle user not found`() {
    `when`(userRepository.findById("999")).thenReturn(Mono.empty())

    StepVerifier.create(userService.findById("999"))
        .expectError(UserNotFoundException::class.java)
        .verify()
}
```

### Configuration

```kotlin
// application.yml
spring:
  application:
    name: my-service
  r2dbc:
    url: r2dbc:postgresql://localhost:5432/mydb
    username: user
    password: password
  webflux:
    base-path: /api

server:
  port: 8080

logging:
  level:
    org.springframework.r2dbc: DEBUG
```

### Best Practices

1. **Never Block in Reactive Code**
   ```kotlin
   // BAD - blocks thread
   fun getUser(id: String): Mono<User> {
       val user = blockingDatabaseCall()  // Don't do this!
       return Mono.just(user)
   }

   // GOOD - stays reactive
   fun getUser(id: String): Mono<User> {
       return userRepository.findById(id)
   }
   ```

2. **Use Appropriate Operators**
   - `flatMap`: Chain async operations
   - `map`: Transform values
   - `filter`: Remove unwanted items
   - `switchIfEmpty`: Provide fallback
   - `zip`: Combine multiple publishers

3. **Handle Errors Properly**
   - Use `onErrorResume` for fallback
   - Use `onErrorReturn` for default values
   - Use `doOnError` for logging

4. **Subscribe Carefully**
   - Spring handles subscription in controllers
   - Only call `subscribe()` in non-controller code when needed
   - Use `StepVerifier` in tests

5. **Mind Backpressure**
   - Reactive Streams handle backpressure automatically
   - Use operators like `buffer()`, `window()` for control

---

## 11. Project-Specific Setup

### Clone and Setup

```bash
# Clone repository
git clone <repository-url>
cd lending-platform-service

# Build project
./gradlew build

# Run tests
./gradlew test
```

### Database Setup

```bash
# Start PostgreSQL with Docker
docker-compose up -d postgres

# Run migrations (if using Flyway)
./gradlew flywayMigrate

# Connect to database
psql -h localhost -U user -d mydb
```

### Running the Application

```bash
# Run locally
./gradlew bootRun

# Run with specific profile
./gradlew bootRun --args='--spring.profiles.active=dev'

# Build JAR
./gradlew build

# Run JAR
java -jar build/libs/app.jar
```

### IntelliJ IDEA Setup

1. **Import Project**: File → Open → Select project root
2. **JDK Setup**: File → Project Structure → Project SDK → Select JDK 17+
3. **Gradle Setup**: Should auto-detect `build.gradle.kts`
4. **Kotlin Plugin**: Should be pre-installed
5. **Enable Annotation Processing**: Settings → Build → Compiler → Annotation Processors

### Code Style

Follow existing patterns in codebase:
- Kotlin naming conventions
- Package organization
- Dependency injection style
- Error handling patterns
- Test structure

### Useful IntelliJ Shortcuts

```
⌘ + N          Generate code (constructor, getters, etc.)
⌘ + O          Find class
⌘ + ⇧ + O      Find file
⌘ + ⌥ + L      Reformat code
⌘ + ⇧ + T      Create/go to test
⌘ + B          Go to declaration
⌘ + ⌥ + B      Go to implementation
⌘ + /          Comment line
⌘ + ⇧ + /      Comment block
^R             Run
^D             Debug
```

---

## 12. Learning Resources

### Official Documentation
- **Kotlin**: https://kotlinlang.org/docs/home.html
- **Spring Boot**: https://spring.io/projects/spring-boot
- **Spring WebFlux**: https://docs.spring.io/spring-framework/reference/web/webflux.html
- **Reactor**: https://projectreactor.io/docs/core/release/reference/
- **PostgreSQL**: https://www.postgresql.org/docs/
- **Gradle**: https://docs.gradle.org/

### Books
- "Kotlin in Action" by Dmitry Jemerov & Svetlana Isakova
- "Reactive Spring" by Josh Long
- "PostgreSQL: Up and Running" by Regina Obe & Leo Hsu

### Online Courses
- Kotlin Koans (interactive Kotlin tutorials)
- Spring Academy (official Spring courses)
- PostgreSQL Tutorial (postgresqltutorial.com)

### Practice
- **LeetCode/HackerRank**: SQL problems
- **Exercism**: Kotlin track
- **GitHub**: Explore open-source Spring Boot projects

### Project Exercises

1. **Week 1-2**: Command line, Git, SQL basics
   - Complete Git branching tutorial
   - Practice SQL queries on sample database
   - Get comfortable with terminal

2. **Week 3-4**: JVM, OOP, Kotlin basics
   - Build simple Kotlin console application
   - Implement OOP concepts (inheritance, polymorphism)
   - Understand JVM memory model

3. **Week 5-6**: PostgreSQL, Gradle
   - Design a database schema for a simple app
   - Create Gradle project from scratch
   - Write complex SQL queries with joins and subqueries

4. **Week 7-8**: Spring WebFlux basics
   - Build REST API with CRUD operations
   - Integrate with PostgreSQL using R2DBC
   - Write integration tests

5. **Week 9-10**: Advanced WebFlux
   - Implement reactive WebClient calls
   - Handle errors properly
   - Combine multiple reactive streams
   - Add proper logging and monitoring

6. **Week 11-12**: Project work
   - Contribute to real project features
   - Code reviews
   - Pair programming

---

## Getting Help

- **Documentation**: Always check official docs first
- **Team Members**: Don't hesitate to ask questions
- **Code Review**: Learn from feedback
- **Pair Programming**: Great way to learn patterns
- **Stack Overflow**: Search before asking
- **IntelliJ Debugger**: Your best friend for understanding code flow

---

## Final Tips

1. **Read existing code**: Best way to understand patterns
2. **Start small**: Don't try to learn everything at once
3. **Practice regularly**: Code every day
4. **Ask questions**: No question is too small
5. **Document learnings**: Keep notes as you learn
6. **Test-driven**: Write tests to understand functionality
7. **Be patient**: Learning takes time, be kind to yourself

Welcome to the team! 🚀
