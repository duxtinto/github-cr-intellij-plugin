## Plugin configuration

1. Set your github credentials in your ide, as indicated by [jetbrain's documentation](https://www.jetbrains.com/help/idea/github.html)
2. Set a git remote for your root repository, which name must be 'origin' or 'github', and which url is pointing to your github repository
3. Make sure that you have properly set passwords for git remoted, as indicated by [jetbrain's documentation](https://www.jetbrains.com/help/idea/using-git-integration.html#set-passwords-for-git-remotes)  

## Known limitations

- If your project contains several repositories, only pull requests for the root repository are detected

## Standing on the shoulders of Giants

[gerrit intellij plugin](https://github.com/uwolfer/gerrit-intellij-plugin):

Infinite source of inspiration for this plugin.  
Its source code is always of great help whenever in doubt about how to interact with the intellij IDE.