# How to set up GitHub as a Backup storage 


1. Create a Private GitHub repository. <br>

2. Do `git init` and `git remote add origin [GitHub_repository]` in the folder, where your notes are. I also use the Main branch, so I did `git branch -M main`. <br>

3. Create a script with the command `touch obsidian-backup.sh` - [obsidian-backup.sh](https://github.com/DenisBuserski/Docs/blob/main/obsidian-backup.sh) <br>

4. Make the script executable with the command - `chmod +x obsidian-backup.sh`. <br>

5. Create a cron. Run `crontab -e` and you will get a menu like this:
  ```
  pc-user@personal-pc:~/Apps$ crontab -e
  no crontab for pc-user - using an empty one
  Select an editor. To change later, run 'select-editor'.
    1. /bin/nano    <---- easiest
    2. /usr/bin/vim.basic
    3. /usr/bin/vim.tiny
    4. /bin/ed
  Choose 1-4 [1]:
  ```
  Select 1(For "nano"). Then add `0 * * * * /home/yourusername/obsidian-backup.sh >> /home/yourusername/obsidian-backup.log 2>&1`. Save the file - `Ctrl + O`. Press `Enter` to confirm and press `Ctrl + X` to exit nano. <br>

6. Using `crontab -l` you can check the cron you created. <br>

Everything is set now. Enjoy!
