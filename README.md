# survival iets ding

- evolutie
  - wezen van speler
    = speler bepaald
    - pairen met andere wezens -> beïnvloedt permanente eigenschappen
    - voeding enz. -> tijdelijke eigenschap
  - andere wezens in wereld
    = bepalen zelf (random/evt. later neural network)
    - verder zelfde als wezen van speler
- omgeving
  = computer bepaald (volledig random)
  - invloed (temp, nat, enz.)
- doel
  = survival
  - omstandigheden steeds moeilijker
  - als enige overblijven

- World
  - Creatures
    - Codes (DNA) -> PropContainer
      - Speed, damage, max energy, resistance, enz.. -> Code -> Prop
      - Changed by pairing
    - Stats -> PropContainer
      - Energy -> Stat -> Prop
        - Changed by actions (based on env)
    - Actions
      - Eat, Pair -> Action
      - Random/Neural network/players
  - Environment
    - Temp, weather, enz..

Usefull websites:
- https://nathanrooy.github.io/posts/2017-11-30/evolving-simple-organisms-using-a-genetic-algorithm-and-deep-learning/