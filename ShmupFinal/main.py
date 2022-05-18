# Shmup game
# Frozen Jam by tgfcoder <https://twitter.com/tgfcoder> licensed under CC-BY-3
# shooting sound and game graphics from Kenney.nl on opengameart.org
import pygame as pg
import random
# https://youtu.be/vvgWfNLgK9c?t=361
# lol send chris the youtube link to that "flappy bird rtx" video using discord
# when the ship gets hit it should use pg.rotate 3 times and ragdoll while it does that
import pygame.sprite
from os import path

# set up this one to jump into your img directory, grab the file that has a name passed in below
img_dir = path.join(path.dirname(__file__), 'img')
# use this one to jump in at the img folder, then jump one more level into meteors so you can grab meteors from the right directory
meteor_dir = path.join(img_dir, "meteors")
#find our sounds folder and jump in
snd_dir = path.join(path.dirname(__file__), 'snd')

WIDTH = 480
HEIGHT = 600
FPS = 60

# define colors
WHITE = (255, 255, 255)
BLACK = (0, 0, 0)
RED = (255, 0, 0)
GREEN = (0, 255, 0)
BLUE = (0, 0, 255)
YELLOW = (255, 255, 0)

# initialize pygame
pg.init()
pg.mixer.init()
screen = pygame.display.set_mode((WIDTH, HEIGHT))
Title = pg.display.set_caption("Shoot Up GO!")
clock = pg.time.Clock()

# set a font_name variable to match a version of arial from the list of fonts on the computer running the code,
# so as to not produce errors
font_name = pg.font.match_font('arial')
def draw_text(surf, text, size, x, y):
    font = pg.font.Font(font_name, size)
    #true sets anti_aliasing to true and just makes it look smooth with some whiter pixels
    text_surface = font.render(text, True, WHITE)
    # the text rect needs to come from somewhere and that somewhere is the text_surface which is like the base that we're
    # now building on, it's like a thing that exists but were making it tangible
    text_rect = text_surface.get_rect()
    text_rect.midtop = (x,y)
    surf.blit(text_surface, text_rect)

def newmob():
    m = Mob()
    ## add to sprite groups
    all_sprites.add(m)
    mobs.add(m)

class Player(pg.sprite.Sprite):
    def __init__(self):
        # this next line that says super... just works better than "pygame.sprite.Sprite.__init__(self)"
        super(Player, self).__init__()
        self.image = pg.transform.scale(player_img, (50, 38))
        # colorkey makes a color disappear from the image, like the black background of it so it can show the things behind it
        self.rect = self.image.get_rect()
        self.image.set_colorkey(BLACK)
        # comment explaining self.rect.centerx line up the center of the sprite with the middle of the screen by dividing
        # our defined window width by 2 and making that the spot where the sprite goes
        # #commented code#  self.radius = 20
        # #commented code# pygame.draw.circle(self.image, RED, self.rect.center, self.radius)
        self.rect.centerx = WIDTH / 2
        # align the sprite 10 pixels away from the bottom of our defined window
        self.rect.bottom = HEIGHT - 10
        self.speedx = 0
        self.shield = 100

    def update(self):
        self.rect.x += self.speedx
        self.speedx = 0
        #try to implement mouse motion instead
        keystate = pg.key.get_pressed()
        if keystate[pg.K_a] or keystate[pg.K_LEFT]:
            self.speedx = -7
        if keystate[pg.K_d] or keystate[pg.K_RIGHT]:
            self.speedx = 7
        if self.rect.right > WIDTH:
            self.rect.right = WIDTH
        # width is simply a name for our defined horizontal space, we half it to get center and by the same logic,
        # we use zero to get the left side because that's where it starts in this case, leaving only the width name for
        # the max. I think you can use beyond the max which is beyond the window, or hardcoding it to the max value
        # would set it beyond the screen too because the sprite starts at the maximum and goes on
        if self.rect.left < 0:
            self.rect.left = 0

    # Believe in yourself and your ability to code, you were ahead of the game when you got to that one part of the video.
    # You hardcoded a workaround for yellow when you thought there was no preset. You knew there should be a corresponding
    # piece of code for bullets.add when it was having an error within def shoot, and then the guy in the video started
    # making that piece so the error would disappear

    def shoot(self):
        bullet = Bullet(self.rect.centerx, self.rect.top)
        all_sprites.add(bullet)
        bullets.add(bullet)
        #pygame.mixer.Sound.set_volume(4)
        finalshootSound = random.choice(shoot_sound)
        finalshootSound.set_volume(0.4)
        finalshootSound.play()


class Mob(pg.sprite.Sprite):
    def __init__(self):
        pg.sprite.Sprite.__init__(self)
        self.image_orig = random.choice(meteor_images)
        self.image_orig.set_colorkey(BLACK)
        self.image = self.image_orig.copy()
        self.rect = self.image.get_rect()
        self.radius = int(self.rect.width * 0.9 / 2)
        # get a random number to be the spot in the horizontal range for enemy ships to appear, making sure that they
        # don't spawn off the edge by setting the entire width - the width of the ships, so the max that they can appear
        # is always on screen. Make sure to subtract the all-lowercase width because its an attribute of the sprite we
        # are taking away from the screen size
        self.rect.x = random.randrange(0, (WIDTH - self.rect.width))
        self.rect.y = random.randrange(-100, -40)
        self.speedy = random.randrange(1, 8)
        self.speedx = random.randrange(-3, 3)
        self.spin = 0
        self.spin_speed = random.randrange(-8, 8)
        self.last_update = pg.time.get_ticks()

    def rotate(self):
        now = pg.time.get_ticks()
        if now - self.last_update > 50:
            self.last_update = now
            self.spin = (self.spin + self.spin_speed) % 360
            self.image = pg.transform.rotate(self.image_orig, self.spin)

    def update(self):
        self.rotate
        self.rect.y += self.speedy
        self.rect.x += self.speedx
        if self.rect.top > HEIGHT + 10 or (self.rect.left < -25 or self.rect.right > WIDTH + 20):
            self.rect.x = random.randrange(0, WIDTH - self.rect.width)
            self.rect.y = random.randrange(-100, -40)
            self.speedy = random.randrange(1, 8)


class Bullet(pg.sprite.Sprite):

    def __init__(self, x, y):
        pg.sprite.Sprite.__init__(self)
        self.image = bullet_img
        self.image.set_colorkey(BLACK)
        # dont get too cocky though because hard coding yellow made it break
        # self.image.fill(YELLOW)
        self.rect = self.image.get_rect()
        self.rect.bottom = y
        self.rect.centerx = x
        self.speedy = -10

    def update(self):
        self.rect.y += self.speedy
        # kill the bullet sprites if it moves off the top of the screen
        if self.rect.bottom < 0:
            self.kill()


# load all game graphics
background = pg.image.load(path.join(img_dir, "starfield.png")).convert()
background_rect = background.get_rect()
player_img = pg.image.load(path.join(img_dir, "playerShip1_blue.png")).convert()
meteor_img = pg.image.load(path.join(meteor_dir, "mediumMeteor.png")).convert()
bullet_img = pg.image.load(path.join(img_dir, "laserRed16.png")).convert()
meteor_images = []
meteor_list = ['meteorBrown_big4.png', 'meteorBrown_big3.png', 'meteorBrown_med1.png', 'meteorBrown_med3.png',
               'meteorBrown_small1.png', 'meteorBrown_small2.png', 'mediumMeteor.png', 'meteorBrown_tiny1.png', ]
for img in meteor_list:
    meteor_images.append(pg.image.load(path.join(meteor_dir, img)).convert())
#load all game sounds
shoot_sound = []
for snd in ['laser1.wav', 'laser2.wav']:
    shoot_sound.append(pg.mixer.Sound(path.join(snd_dir, snd)))
# do this one if you want to set up a single sound instead of multiple:
# shoot_sound = pg.mixer.Sound(path.join(snd_dir, 'laser2.ogg'))
expl_sounds = []
for snd in ['expl3.wav', 'expl6.wav']:
    expl_sounds.append(pg.mixer.Sound(path.join(snd_dir, snd)))
    #im pretty sure python straigtup hates ogg files so convert the seamless frozen something to wav
pg.mixer.music.load(path.join(snd_dir, 'tgfcoder-FrozenJam-SeamlessLoop.wav'))
pg.mixer.music.set_volume(0.4)

# sprite group creation
all_sprites = pg.sprite.Group()
mobs = pg.sprite.Group()
bullets = pg.sprite.Group()

# game object creation
for i in range(8):
    newmob()
score = 0
pg.mixer.music.play(loops=-1)

# add player to all sprites group
player = Player()
all_sprites.add(player)

# game loop
running = True
while running:
    # keep loop running at the right speed
    clock.tick(FPS)
    # process input events
    for event in pg.event.get():
        # check for closing window
        if event.type == pg.QUIT:
            running = False
        elif event.type == pg.KEYDOWN:
            if event.key == pg.K_SPACE:
                player.shoot()

    # update
    all_sprites.update()

    # check to see if a bullet hit a mob, if collision successful: kill mobs kill bullets with true and true
    hits = pg.sprite.groupcollide(mobs, bullets, True, True)
    for hit in hits:
        # don't set "hits += 50 - hit.radius" because it throws a typeError on the math and also we're supposed to
        # be updating the score not the radius
        score += 50 - hit.radius
        #pygame.mixer.Sound.set_volume(4)
        finalExplsound = random.choice(expl_sounds)
        finalExplsound.set_volume(0.4)
        finalExplsound.play()
        newmob()

    # check to see if a mob hit the player and because we want the mob to die when we still have a shield, we set it to
    # false, and because using circle collision rather than rectangle grid collision,
    hits = pg.sprite.spritecollide(player, mobs, True, pg.sprite.collide_circle)
    for hit in hits:
        player.shield -= hit.radius * 2
        newmob()
        if player.shield <= 0:
            running = False

    # Draw / render
    screen.fill(BLUE)
    screen.blit(background, background_rect)
    all_sprites.draw(screen)
    draw_text(screen, str(score), 18, WIDTH /2, 15)
    # drawing before oar after all sprites will put the score behind or in front of the meteors (I want the meteors
    # to go in front of it)
    # 𝘢𝘧𝘵𝘦𝘳 drawing everything flip the canvas
    pg.display.flip()

pg.quit()

# https://youtu.be/_y5U8tB36Vk?t=576
